/*
 * Copyright 2014 agwlvssainokuni
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cherry.foundation.mail;

import java.io.File;

import org.joda.time.LocalDateTime;
import org.springframework.dao.DataAccessException;
import org.springframework.mail.MailException;

import cherry.foundation.batch.ExitStatus;
import cherry.foundation.batch.IBatch;
import cherry.foundation.bizdtm.BizDateTime;
import cherry.goods.log.Log;
import cherry.goods.log.LogFactory;

public class SendMailBatch implements IBatch {

	private final Log log = LogFactory.getLog(getClass());

	private BizDateTime bizDateTime;

	private MailSendHandler mailSendHandler;

	private long intervalMillis;

	private File shutdownTrigger;

	public void setBizDateTime(BizDateTime bizDateTime) {
		this.bizDateTime = bizDateTime;
	}

	public void setMailSendHandler(MailSendHandler mailSendHandler) {
		this.mailSendHandler = mailSendHandler;
	}

	public void setIntervalMillis(long intervalMillis) {
		this.intervalMillis = intervalMillis;
	}

	public void setShutdownTrigger(File shutdownTrigger) {
		this.shutdownTrigger = shutdownTrigger;
	}

	@Override
	public ExitStatus execute(String... args) {
		while (!shutdownTrigger.exists()) {
			sendMail();
			sleep();
		}
		deleteShutdownTrigger();
		return ExitStatus.NORMAL;
	}

	private void sendMail() {
		try {
			LocalDateTime now = bizDateTime.now();
			for (long messageId : mailSendHandler.listMessage(now)) {
				mailSendHandler.sendMessage(messageId);
			}
		} catch (MailException | DataAccessException ex) {
			if (log.isDebugEnabled()) {
				log.debug(ex, "failed to send mail");
			}
		}
	}

	private void sleep() {
		try {
			Thread.sleep(intervalMillis);
		} catch (InterruptedException ex) {
			if (log.isDebugEnabled()) {
				log.debug(ex, "Interrupted");
			}
		}
	}

	private void deleteShutdownTrigger() {
		if (log.isDebugEnabled()) {
			log.debug("Deleting shutdownTrigger: {0}",
					shutdownTrigger.getAbsolutePath());
		}
		boolean result = shutdownTrigger.delete();
		if (log.isDebugEnabled()) {
			if (result) {
				log.debug("Deleted shutdownTrigger");
			} else {
				log.debug("Failed to delete shutdownTrigger");
			}
		}
	}

}
