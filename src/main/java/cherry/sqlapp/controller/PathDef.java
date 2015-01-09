/*
 * Copyright 2014,2015 agwlvssainokuni
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

package cherry.sqlapp.controller;

public class PathDef {

	public static final String URI_LOGIN = "/login";

	public static final String URI_HOME = "/secure";

	public static final String URI_SQLTOOL_SEARCH = "/sqltool/search";

	public static final String URI_SQLTOOL_CLAUSE = "/sqltool/clause";

	public static final String URI_SQLTOOL_CLAUSE_ID = "/sqltool/clause/{id}";

	public static final String URI_SQLTOOL_STATEMENT = "/sqltool/statement";

	public static final String URI_SQLTOOL_STATEMENT_ID = "/sqltool/statement/{id}";

	public static final String URI_SQLTOOL_LOAD = "/sqltool/load";

	public static final String URI_SQLTOOL_LOAD_ID = "/sqltool/load/{id}";

	public static final String SUBURI_EXECUTE = "execute";

	public static final String SUBURI_FINISH = "finish";

	public static final String SUBURI_METADATA = "metadata";

	public static final String METHOD_LOGIN_FAILED = "loginFailed";

	public static final String METHOD_LOGGED_OUT = "loggedOut";

	public static final String METHOD_DOWNLOAD = "download";

	public static final String METHOD_CREATE = "create";

	public static final String METHOD_UPDATE = "update";

	public static final String PATH_VAR_ID = "id";

	public static final String PARAM_REF = "ref";

	public static final String VIEW_LOGIN_INIT = "login/init";

	public static final String VIEW_HOME_INIT = "secure/home/init";

	public static final String VIEW_SQLTOOL_SEARCH_INIT = "sqltool/search/init";

	public static final String VIEW_SQLTOOL_CLAUSE_INIT = "sqltool/clause/init";

	public static final String VIEW_SQLTOOL_CLAUSE_ID_INIT = "sqltool/clause/initId";

	public static final String VIEW_SQLTOOL_STATEMENT_INIT = "sqltool/statement/init";

	public static final String VIEW_SQLTOOL_STATEMENT_ID_INIT = "sqltool/statement/initId";

	public static final String VIEW_SQLTOOL_LOAD_INIT = "sqltool/load/init";

	public static final String VIEW_SQLTOOL_LOAD_FINISH = "sqltool/load/finish";

	public static final String VIEW_SQLTOOL_LOAD_ID_INIT = "sqltool/load/initId";

	public static final String VIEW_SQLTOOL_LOAD_ID_FINISH = "sqltool/load/finishId";

}
