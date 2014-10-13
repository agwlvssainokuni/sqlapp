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

package cherry.gradle.task.generator;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class RowBasedParser implements WorkbookParser {

	@Override
	public List<TypeDef> parse(Workbook workbook) {
		List<TypeDef> list = new LinkedList<>();
		int numOfSheets = workbook.getNumberOfSheets();
		for (int i = 0; i < numOfSheets; i++) {
			Sheet sheet = workbook.getSheetAt(i);
			list.addAll(parseSheet(sheet));
		}
		return list;
	}

	private List<TypeDef> parseSheet(Sheet sheet) {

		boolean configured = false;
		int coldefFirstCellNum = -1;
		Map<Integer, String> coldef = new TreeMap<>();

		Map<String, TypeDef> map = new LinkedHashMap<>();

		TypeDef typeDef = null;
		for (Row row : sheet) {

			int firstCellNum = row.getFirstCellNum();
			if (firstCellNum < 0) {
				continue;
			}

			if (!configured) {

				String directive = getCellValueAsString(row
						.getCell(firstCellNum));
				if ("##COLDEF".equals(directive)) {

					for (Cell cell : row) {
						if (cell.getColumnIndex() == firstCellNum) {
							continue;
						}
						if (cell.getCellType() != Cell.CELL_TYPE_STRING) {
							continue;
						}
						coldef.put(cell.getColumnIndex(),
								getCellValueAsString(cell));
					}

					coldefFirstCellNum = firstCellNum;
					configured = true;
				} else {
					// IGNORE UNKNOWN DIRECTIVES
				}
			} else {

				ItemDef item = null;
				for (Cell cell : row) {

					if (cell.getColumnIndex() == coldefFirstCellNum) {
						item = new ItemDef();
						continue;
					}
					if (item == null) {
						continue;
					}
					String key = coldef.get(cell.getColumnIndex());
					if (key == null) {
						continue;
					}

					String value = getCellValueAsString(cell);
					if (value != null) {
						item.put(key, value);
					}
				}

				if (item != null) {
					String fqcn = item.get(TypeDef.FULLY_QUALIFIED_CLASS_NAME);
					if (fqcn != null) {
						TypeDef td = map.get(fqcn);
						if (td != null) {
							typeDef = td;
						} else {
							typeDef = new TypeDef();
							typeDef.setSheetName(sheet.getSheetName());
							map.put(fqcn, typeDef);
						}
					}
					if (typeDef != null) {
						typeDef.getItemDef().add(item);
					}
				}
			}
		}

		return new ArrayList<>(map.values());
	}

	private String getCellValueAsString(Cell cell) {
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			return cell.getStringCellValue();
		case Cell.CELL_TYPE_NUMERIC:
			return String.valueOf((int) cell.getNumericCellValue());
		case Cell.CELL_TYPE_FORMULA:
			switch (cell.getCachedFormulaResultType()) {
			case Cell.CELL_TYPE_STRING:
				return cell.getStringCellValue();
			case Cell.CELL_TYPE_NUMERIC:
				return String.valueOf((int) cell.getNumericCellValue());
			default:
				return null;
			}
		default:
			return null;
		}
	}

}
