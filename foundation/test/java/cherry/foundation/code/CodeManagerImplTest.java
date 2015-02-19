package cherry.foundation.code;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cherry.foundation.type.Code;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext-test.xml")
public class CodeManagerImplTest {

	@Autowired
	private CodeManager codeManager;

	public enum CodeType implements Code<String> {
		NONE, CODE0, CODE1;
		@Override
		public String code() {
			return name();
		}
	}

	@Test
	public void testIsValidValue_byENUM() {
		assertFalse(codeManager.isValidValue(CodeType.NONE, "01"));
		assertTrue(codeManager.isValidValue(CodeType.CODE0, "01"));
		assertTrue(codeManager.isValidValue(CodeType.CODE0, "02"));
		assertFalse(codeManager.isValidValue(CodeType.CODE0, "03"));
	}

	@Test
	public void testIsValidValue_btNAME() {
		assertFalse(codeManager.isValidValue("NONE", "01"));
		assertTrue(codeManager.isValidValue("CODE0", "01"));
		assertTrue(codeManager.isValidValue("CODE0", "02"));
		assertFalse(codeManager.isValidValue("CODE0", "03"));
	}

	@Test
	public void testFindByValue_byENUM() {

		try {
			codeManager.findByValue(CodeType.NONE, "01");
			fail("Exception must be thrown");
		} catch (IllegalArgumentException ex) {
			assertEquals("Not defined for (NONE, 01)", ex.getMessage());
		}

		CodeEntry entry01 = codeManager.findByValue(CodeType.CODE0, "01");
		assertEquals("01", entry01.getValue());
		assertEquals("01 - LABEL01", entry01.getLabel());
		assertEquals(0, entry01.getSortOrder());
		CodeEntry entry01p = codeManager.findByValue(CodeType.CODE0, "01", true);
		assertEquals("01", entry01p.getValue());
		assertEquals("LABEL01", entry01p.getLabel());
		assertEquals(0, entry01p.getSortOrder());

		CodeEntry entry02 = codeManager.findByValue(CodeType.CODE0, "02");
		assertEquals("02", entry02.getValue());
		assertEquals("02 - LABEL02", entry02.getLabel());
		assertEquals(0, entry02.getSortOrder());
		CodeEntry entry02p = codeManager.findByValue(CodeType.CODE0, "02", true);
		assertEquals("02", entry02p.getValue());
		assertEquals("LABEL02", entry02p.getLabel());
		assertEquals(0, entry02p.getSortOrder());

		try {
			codeManager.findByValue(CodeType.CODE0, "03");
			fail("Exception must be thrown");
		} catch (IllegalArgumentException ex) {
			assertEquals("Not defined for (CODE0, 03)", ex.getMessage());
		}
	}

	@Test
	public void testFindByValue_byNAME() {

		try {
			codeManager.findByValue("NONE", "01");
			fail("Exception must be thrown");
		} catch (IllegalArgumentException ex) {
			assertEquals("Not defined for (NONE, 01)", ex.getMessage());
		}

		CodeEntry entry01 = codeManager.findByValue("CODE0", "01");
		assertEquals("01", entry01.getValue());
		assertEquals("01 - LABEL01", entry01.getLabel());
		assertEquals(0, entry01.getSortOrder());
		CodeEntry entry01p = codeManager.findByValue("CODE0", "01", true);
		assertEquals("01", entry01p.getValue());
		assertEquals("LABEL01", entry01p.getLabel());
		assertEquals(0, entry01p.getSortOrder());

		CodeEntry entry02 = codeManager.findByValue("CODE0", "02");
		assertEquals("02", entry02.getValue());
		assertEquals("02 - LABEL02", entry02.getLabel());
		assertEquals(0, entry02.getSortOrder());
		CodeEntry entry02p = codeManager.findByValue("CODE0", "02", true);
		assertEquals("02", entry02p.getValue());
		assertEquals("LABEL02", entry02p.getLabel());
		assertEquals(0, entry02p.getSortOrder());

		try {
			codeManager.findByValue("CODE0", "03");
			fail("Exception must be thrown");
		} catch (IllegalArgumentException ex) {
			assertEquals("Not defined for (CODE0, 03)", ex.getMessage());
		}
	}

	@Test
	public void testGetCodeList_byENUM() {

		List<CodeEntry> code0 = codeManager.getCodeList(CodeType.CODE0);
		List<CodeEntry> code0p = codeManager.getCodeList(CodeType.CODE0, true);

		CodeEntry entry01 = code0.get(0);
		assertEquals("01", entry01.getValue());
		assertEquals("01 - LABEL01", entry01.getLabel());
		assertEquals(0, entry01.getSortOrder());
		CodeEntry entry01p = code0p.get(0);
		assertEquals("01", entry01p.getValue());
		assertEquals("LABEL01", entry01p.getLabel());
		assertEquals(0, entry01p.getSortOrder());

		CodeEntry entry02 = code0.get(1);
		assertEquals("02", entry02.getValue());
		assertEquals("02 - LABEL02", entry02.getLabel());
		assertEquals(0, entry02.getSortOrder());
		CodeEntry entry02p = code0p.get(1);
		assertEquals("02", entry02p.getValue());
		assertEquals("LABEL02", entry02p.getLabel());
		assertEquals(0, entry02p.getSortOrder());
	}

	@Test
	public void testGetCodeList_byNAME() {

		List<CodeEntry> code0 = codeManager.getCodeList("CODE0");
		List<CodeEntry> code0p = codeManager.getCodeList("CODE0", true);

		CodeEntry entry01 = code0.get(0);
		assertEquals("01", entry01.getValue());
		assertEquals("01 - LABEL01", entry01.getLabel());
		assertEquals(0, entry01.getSortOrder());
		CodeEntry entry01p = code0p.get(0);
		assertEquals("01", entry01p.getValue());
		assertEquals("LABEL01", entry01p.getLabel());
		assertEquals(0, entry01p.getSortOrder());

		CodeEntry entry02 = code0.get(1);
		assertEquals("02", entry02.getValue());
		assertEquals("02 - LABEL02", entry02.getLabel());
		assertEquals(0, entry02.getSortOrder());
		CodeEntry entry02p = code0p.get(1);
		assertEquals("02", entry02p.getValue());
		assertEquals("LABEL02", entry02p.getLabel());
		assertEquals(0, entry02p.getSortOrder());
	}

	@Test
	public void testGetCodeMap_byENUM() {

		Map<String, String> code0 = codeManager.getCodeMap(CodeType.CODE0);
		Map<String, String> code0p = codeManager.getCodeMap(CodeType.CODE0, true);

		assertEquals("01 - LABEL01", code0.get("01"));
		assertEquals("LABEL01", code0p.get("01"));
		assertEquals("02 - LABEL02", code0.get("02"));
		assertEquals("LABEL02", code0p.get("02"));
	}

	@Test
	public void testGetCodeMap_byNAME() {

		Map<String, String> code0 = codeManager.getCodeMap("CODE0");
		Map<String, String> code0p = codeManager.getCodeMap("CODE0", true);

		assertEquals("01 - LABEL01", code0.get("01"));
		assertEquals("LABEL01", code0p.get("01"));
		assertEquals("02 - LABEL02", code0.get("02"));
		assertEquals("LABEL02", code0p.get("02"));
	}

}
