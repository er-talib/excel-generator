package com.excel.config;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.excel.entity.Proposal;
import com.excel.entity.SAPAmount;
import com.excel.entity.StateActionPlan;
import com.fasterxml.jackson.databind.JsonNode;

@Service
public class ExcelGeneratorConfig {

	public static ByteArrayInputStream excelGenerator(List<Proposal> proposals) throws IOException {

		List<Proposal> listCSWProposal = proposals.stream().filter(p -> p.getSector().equals("CSW"))
				.collect(Collectors.toList());

		List<Proposal> listCSTProposal = proposals.stream().filter(p -> p.getSector().equals("CST"))
				.collect(Collectors.toList());

		String[] columns = { "S.No", "Name of the ULB", "Present Population", "2011 Census Population",
				"Present households", "2025 projected population", "2025 projected households",
				"Current Sewage Generation (MLD)", "Projected sewage generation for 2025 (MLD)",
				"Septage generation at present(KLD) *Only for frindge areas",
				"Projected septage generation in 2025 (KLD) *Only for frindge areas", "ULB", "Private", "Total", "ULB",
				"Private", "Total", "FSTP Capacity available at present (KLD)",
				"Septage treatment Capacity required in 2025 (KLD)", "No.", "Amount (Rs. in Lakh)", "No.",
				"Amount (Rs. in Lakh)", "Length (75 cm width) of the drain to be provided / retrofitted in KMs",
				"Amount (Rs. in Lakh)", "No. Of outfalls", "Nos. of I&D Proposed", "Dia. & Material of pipe",
				"Length of pipe (km)", "Amount (Rs. in Lakh)", "No. of Pumping Stations", "Capacity",
				"Amount (Rs. in Lakh)", "Capacity (KLD)", "Amount (Rs. in Lakh)", "Quantity of Sewage, KL",
				"No. of STP Proposed", "Total Capacity of STPs Proposed, MLD", "Amount (Rs. in Lakh)",
				"Total amount required (Rs. in Lakh) 39=20+22+24+29+29+32+34+38",
				"Central Share sought (Rs. in Lakh)" };

		String[] columns2 = { "S.No", "Name of the ULB", "Population as per 2011 census", "Current household",

				"Current Population", "2025 projected population", "projected households in 2025",
				"Floating Population", "IHHL, nos.", "CT, no. of seats", "PT, no. of seats", "Urinals, nos.",
				"Existing units IHHLs, nos.", "No.", "Amount required Rs in Lakh (Rs. 30000/unit) ",
				"Central Share ( In crore)", "No.", "Seat", "No.", "Seat",
				"Amount required Rs in Lakh (Rs 1.5 Lakh/ seat) ", "Central Share (in Crore)", "No.", "Seat", "No.",
				"Seat", "Amount required Rs in Lakh (Rs 2.5 Lakh/ seat) ", "Central Share (In Crore )", "No.", "Seat",
				"No.", "Seat", "Amount required Rs in Lakh (Rs 32000 /urinal ) ", "Central Share (In Crore )",
				"Total Cost, Rs in Lakh", "Central Share, Rs in Lakh" };

		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream output = new ByteArrayOutputStream();) {
			Sheet sheet = workbook.createSheet("Used Water");
			Sheet sheet1 = workbook.createSheet("Toilet");

			Font headFont = workbook.createFont();
			headFont.setBold(true);
			headFont.setColor(IndexedColors.BLACK.getIndex());

			Font headFont1 = workbook.createFont();
			headFont1.setBold(true);
			headFont1.setColor(IndexedColors.BLACK.getIndex());

			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headFont);
			headerCellStyle.setAlignment(HorizontalAlignment.LEFT);

			CellStyle headerCellStyle1 = workbook.createCellStyle();
			headerCellStyle1.setFont(headFont1);
			headerCellStyle1.setAlignment(HorizontalAlignment.LEFT);

			Row row = sheet.createRow(0);

			Cell cell2 = row.createCell(0);
			cell2.setBlank();
			sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 10));

			Cell cell3 = row.createCell(11);
			cell3.setCellStyle(headerCellStyle);
			cell3.setCellValue("Cesspool tankers presently available");
			sheet.addMergedRegion(new CellRangeAddress(0, 1, 11, 13));

			Cell cell4 = row.createCell(14);
			cell4.setCellStyle(headerCellStyle);
			cell4.setCellValue("Cesspool tankers requirement 2025");
			sheet.addMergedRegion(new CellRangeAddress(0, 1, 14, 16));

			Cell cell5 = row.createCell(17);
			cell5.setBlank();
			sheet.addMergedRegion(new CellRangeAddress(0, 1, 17, 18));
			Cell cell6 = row.createCell(19);
			cell6.setCellStyle(headerCellStyle);

			cell6.setCellValue("Cesspool tankers proposed");
			sheet.addMergedRegion(new CellRangeAddress(0, 1, 19, 20));
			Cell cell7 = row.createCell(21);
			cell7.setCellStyle(headerCellStyle);

			cell7.setCellValue("Any other cleaning equipment proposed");
			sheet.addMergedRegion(new CellRangeAddress(0, 1, 21, 22));
			Cell cell8 = row.createCell(23);
			cell8.setCellStyle(headerCellStyle);

			cell8.setCellValue("I&D Infrastructure");
			sheet.addMergedRegion(new CellRangeAddress(0, 1, 23, 32));

			Cell cell9 = row.createCell(33);
			cell9.setCellStyle(headerCellStyle);
			cell9.setCellValue("Septage receiving facility proposed (KLD)");
			sheet.addMergedRegion(new CellRangeAddress(0, 1, 33, 34));

			Cell cell10 = row.createCell(35);
			cell10.setCellStyle(headerCellStyle);
			cell10.setCellValue("Treatment Systems");
			sheet.addMergedRegion(new CellRangeAddress(0, 1, 35, 38));

			Row headerRow = sheet.createRow(2);

			Row headerRow1 = sheet1.createRow(2);

			for (int col = 0; col < columns.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(columns[col]);
				cell.setCellStyle(headerCellStyle);
				sheet.setColumnWidth(col, 5000);
			}

			for (int col = 0; col < columns2.length; col++) {
				Cell cell = headerRow1.createCell(col);
				cell.setCellValue(columns2[col]);
				cell.setCellStyle(headerCellStyle1);
				sheet1.setColumnWidth(col, 5000);

			}

			int rowInd = 3;
			int sNo = 1;
			for (Proposal proposal : listCSWProposal) {
//
//				String[] str = { "S.No", "Name of the ULB", "Present Population", "2011 Census Population",
//						"Present households", "2025 projected population", "2025 projected households",
//						"Current Sewage Generation (MLD)", "Projected sewage generation for 2025 (MLD)",
//						"Septage generation at present(KLD) *Only for frindge areas",
//						"Projected septage generation in 2025 (KLD) *Only for frindge areas", "ULB", "Private", "Total",
//						"ULB", "Private", "Total", "FSTP Capacity available at present (KLD)",
//						"Septage treatment Capacity required in 2025 (KLD)", "No.", "Amount (Rs. in Lakh)", "No.",
//						"Amount (Rs. in Lakh)", "Length (75 cm width) of the drain to be provided / retrofitted in KMs",
//						"Amount (Rs. in Lakh)", "No. Of outfalls", "Nos. of I&D Proposed", "Dia. & Material of pipe",
//						"Length of pipe (km)", "Amount (Rs. in Lakh)", "No. of Pumping Stations", "Capacity",
//						"Amount (Rs. in Lakh)", "Capacity (KLD)", "Amount (Rs. in Lakh)", "Quantity of Sewage, KL",
//						"No. of STP Proposed", "Total Capacity of STPs Proposed, MLD", "Amount (Rs. in Lakh)",
//						"Total amount required (Rs. in Lakh) 39=20+22+24+29+29+32+34+38",
//						"Central Share sought (Rs. in Lakh)" };

				JsonNode jsonNode = proposal.getGapAnalysis().getAnalysisJSON();

				int presentPopulation = jsonNode.get("sections").get(0).get("items").get(1).get(3).get("value").asInt();
				int censusPopulation2011 = jsonNode.get("sections").get(0).get("items").get(1).get(2).get("value")
						.asInt();
				int projectedPopulation2025 = jsonNode.get("sections").get(0).get("items").get(1).get(4).get("value")
						.asInt();
				int presenthouseholds = jsonNode.get("sections").get(0).get("items").get(2).get(3).get("value").asInt();
				int projectedHouseholds2025 = jsonNode.get("sections").get(0).get("items").get(2).get(4).get("value")
						.asInt();

//				double currentSewageGenerationMLD = jsonNode.get("sections").get(5).get("items").get(2).get(0).get("value").asDouble();
//				double projectedSewageGenerationFor2025MLD = jsonNode.get("sections").get(5).get("items").get(2).get(1).get("value").asDouble();
//				int fSTPCapacityAvailableAtPresentKLD = jsonNode.get("sections").get(11).get("items").get(1).get(3)
//						.get("value").asInt();
//				int length75CmWidthOfTheDrainToBeProvidedRetrofittedInKMs  = jsonNode.get("sections").get(12).get("items").get(1).get(5).get("value").asInt();

				Integer nosOfIAndDProposed = jsonNode.get("sections").get(16).get("items").get(3).get(3).get("value")
						.asInt();
//				String diaAndMaterialOfPipe  = jsonNode.get("sections").get(7).get("items").get(0).get(2).get("value").asText();
//				String lengthOfPipeKm  = jsonNode.get("sections").get(7).get("items").get(0).get(4).get("value").asText();
//				int totalCapacityOfSTPProposedMLD  = jsonNode.get("sections").get(9).get("items").get(5).get(3).get("value").asInt();

				Row row1 = sheet.createRow(rowInd++);
				row1.createCell(0).setCellValue(sNo++);
				row1.createCell(1).setCellValue(proposal.getUlbName());

				row1.createCell(2).setCellValue(presentPopulation);
				row1.createCell(3).setCellValue(censusPopulation2011);
				row1.createCell(4).setCellValue(presenthouseholds);
				row1.createCell(5).setCellValue(projectedPopulation2025);
				row1.createCell(6).setCellValue(projectedHouseholds2025);
//				row1.createCell(7).setCellValue(proposal.getAmountInLakh());
//				row1.createCell(8).setCellValue(proposal.getAmountInLakh());
//				row1.createCell(9).setCellValue(proposal.getCapacity());
//				row1.createCell(10).setCellValue(proposal.getCentralShare());
//				row1.createCell(11).setCellValue(proposal.getCapacity());
//				row1.createCell(12).setCellValue(proposal.getDiaAndMaterialOfPipe());
//				row1.createCell(13).setCellValue(proposal.getDistrictName());
//				row1.createCell(14).setCellValue(proposal.getLength75CM());
//				row1.createCell(15).setCellValue(proposal.getLengthOfPipe());
//				row1.createCell(16).setCellValue(proposal.getNumberOfPumpingStations());
//				row1.createCell(17).setCellValue(fSTPCapacityAvailableAtPresentKLD);
//				row1.createCell(18).setCellValue(proposal.getPrivateTotalFSTP());
				row1.createCell(19).setCellValue(proposal.getUlbCode());
				row1.createCell(20).setCellValue(proposal.getCentralShare());
				row1.createCell(21).setCellValue(proposal.getProposalId());
//				row1.createCell(22).setCellValue(proposal.getDistrictId());
				row1.createCell(23).setCellValue(proposal.getUniqueProposalId());
//				row1.createCell(24).setCellValue(proposal.getDistrictName());
				row1.createCell(25).setCellValue(proposal.getSectorName());
//				row1.createCell(26).setCellValue(proposal.getLength75CM());
//				row1.createCell(27).setCellValue(proposal.getAmountInLakh());
//				row1.createCell(28).setCellValue(proposal.getCapacity());
//				row1.createCell(29).setCellValue(proposal.getCityOrUlb());
//				row1.createCell(30).setCellValue(proposal.getState());
//				row1.createCell(31).setCellValue(proposal.getDiaAndMaterialOfPipe());
//				row1.createCell(32).setCellValue(proposal.getNumberOfSTPProposed());
//				row1.createCell(33).setCellValue(proposal.getNumberOfPumpingStations());
//				row1.createCell(34).setCellValue(proposal.getStateId());
//				row1.createCell(35).setCellValue(proposal.getNumberOfSTPProposed());
//				row1.createCell(36).setCellValue(proposal.getStateName());
				row1.createCell(37).setCellValue(nosOfIAndDProposed);
//				row1.createCell(38).setCellValue(proposal.getDistrictName());
				row1.createCell(39).setCellValue(proposal.getSectorName());
//				row1.createCell(40).setCellValue(proposal.getNumberOfSTPProposed());
				row1.createCell(41).setCellValue(proposal.getCentralShare());

			}

			Row row3 = sheet1.createRow(0);

			Cell cellT1 = row3.createCell(0);
			cellT1.setBlank();
			sheet1.addMergedRegion(new CellRangeAddress(0, 1, 0, 7));

			Cell cellT2 = row3.createCell(8);
			cellT2.setCellStyle(headerCellStyle1);
			cellT2.setCellValue("Total no. of toilets required in 2025");
			sheet1.addMergedRegion(new CellRangeAddress(0, 1, 8, 11));

			Cell cellT3 = row3.createCell(12);
			cellT3.setBlank();
			sheet1.addMergedRegion(new CellRangeAddress(0, 1, 12, 12));

			Cell cellT4 = row3.createCell(13);
			cellT4.setCellStyle(headerCellStyle1);
			cellT4.setCellValue("IHHL Requirement for projected population in 2025");
			sheet1.addMergedRegion(new CellRangeAddress(0, 1, 13, 15));

			Cell cellT5 = row3.createCell(16);
			cellT5.setCellStyle(headerCellStyle1);
			cellT5.setCellValue("Existing Community Toilet");
			sheet1.addMergedRegion(new CellRangeAddress(0, 1, 16, 17));

			Cell cellT6 = row3.createCell(18);
			cellT6.setCellStyle(headerCellStyle1);
			cellT6.setCellValue("Community Toilet Requirement for projected population in 2025");
			sheet1.addMergedRegion(new CellRangeAddress(0, 1, 18, 21));

			Cell cellT7 = row3.createCell(22);
			cellT7.setCellStyle(headerCellStyle1);
			cellT7.setCellValue("Existing Public Toilets");
			sheet1.addMergedRegion(new CellRangeAddress(0, 1, 22, 23));

			Cell cellT8 = row3.createCell(24);
			cellT8.setCellStyle(headerCellStyle1);
			cellT8.setCellValue("Public Toilet Requirement for projected population in 2025");
			sheet1.addMergedRegion(new CellRangeAddress(0, 1, 24, 27));

			Cell cellT9 = row3.createCell(28);
			cellT9.setCellStyle(headerCellStyle1);
			cellT9.setCellValue("Existing urinals");
			sheet1.addMergedRegion(new CellRangeAddress(0, 1, 28, 29));

			Cell cellT10 = row3.createCell(30);
			cellT10.setCellStyle(headerCellStyle1);
			cellT10.setCellValue("Urinals Requirement for projected population in 2025");
			sheet1.addMergedRegion(new CellRangeAddress(0, 1, 30, 33));

			Cell cellT11 = row3.createCell(34);
			cellT11.setBlank();
			sheet1.addMergedRegion(new CellRangeAddress(0, 1, 34, 35));

			int rowInd1 = 3;
			int sNo1 = 1;
			for (Proposal proposal : listCSTProposal) {

				Row row2 = sheet1.createRow(rowInd1++);
				row2.createCell(0).setCellValue(sNo1++);
				row2.createCell(1).setCellValue(proposal.getUlbName());
//				row2.createCell(2).setCellValue(proposal.getPresentPopulation());
//				row2.createCell(3).setCellValue(proposal.getPresentHouseHold());
//				row2.createCell(4).setCellValue(proposal.getPopulationOf2011());
//				row2.createCell(5).setCellValue(proposal.getGapAnalysis().getSector());
//				row2.createCell(6).setCellValue(proposal.getProjectedPopulation2025());
//				row2.createCell(7).setCellValue(proposal.getProjectedHousehold2025());
//				row2.createCell(8).setCellValue(proposal.getAmountInLakh());
//				row2.createCell(9).setCellValue(proposal.getCapacity());
//				row2.createCell(10).setCellValue(proposal.getCentralShare());
//				row2.createCell(11).setCellValue(proposal.getCityOrUlb());
//				row2.createCell(12).setCellValue(proposal.getDiaAndMaterialOfPipe());
//				row2.createCell(13).setCellValue(proposal.getDistrictName());
//				row2.createCell(14).setCellValue(proposal.getLength75CM());
//				row2.createCell(15).setCellValue(proposal.getLengthOfPipe());
//				row2.createCell(16).setCellValue(proposal.getNumberOfPumpingStations());
//				row2.createCell(17).setCellValue(proposal.getNumberOfSTPProposed());
//				row2.createCell(18).setCellValue(proposal.getPrivateTotalFSTP());
				row2.createCell(19).setCellValue(proposal.getUlbCode());
//				row2.createCell(20).setCellValue(proposal.getStateId());
				row2.createCell(21).setCellValue(proposal.getProposalId());
//				row2.createCell(22).setCellValue(proposal.getDistrictId());
				row2.createCell(23).setCellValue(proposal.getUniqueProposalId());
				row2.createCell(24).setCellValue(proposal.getStateName());
				row2.createCell(25).setCellValue(proposal.getSectorName());
//				row2.createCell(26).setCellValue(proposal.getAmountInLakh());
//				row2.createCell(27).setCellValue(proposal.getPresentHouseHold());
//				row2.createCell(28).setCellValue(proposal.getStateId());
				row2.createCell(29).setCellValue(proposal.getUlbName());
				row2.createCell(30).setCellValue(proposal.getState());
//				row2.createCell(31).setCellValue(proposal.getDiaAndMaterialOfPipe());
//				row2.createCell(32).setCellValue(proposal.getNumberOfSTPProposed());

			}
			workbook.write(output);
			return new ByteArrayInputStream(output.toByteArray());

		}
	}

	public static ByteArrayInputStream excelGeneratorForCSP(StateActionPlan stateActionPlans) throws IOException {

		if (stateActionPlans.getSectors().equals("CSW")) {

			String[] columns = { "S.No", "Total Cost", "Total Central Share", "Total State Share", "Total Other Share",
					"Amount 2022-23", "Amount 2023-24", "Amount 2024-25", "Total Action Plan Amount" };

			String[] columns2 = { "S.No", "Action Plan Id", "Ulb Name", "Sector", "Total Proposal Cost",
					"Centeral Share", "State Share", "Ulb Share", "Other Share", "Date", "Stauts" };

			try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream output = new ByteArrayOutputStream();) {
				Sheet sheet = workbook.createSheet("Used Water");

				Font headFont = workbook.createFont();
				headFont.setBold(true);
				headFont.setColor(IndexedColors.BLACK.getIndex());

				CellStyle headerCellStyle = workbook.createCellStyle();
				headerCellStyle.setFont(headFont);
				headerCellStyle.setAlignment(HorizontalAlignment.CENTER);

				Row row3 = sheet.createRow(0);
				Cell cellT1 = row3.createCell(0);
				cellT1.setBlank();
				sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 2));

				Cell cellT2 = row3.createCell(3);
				cellT2.setCellStyle(headerCellStyle);
				cellT2.setCellValue("State Action Plan Details");
				sheet.addMergedRegion(new CellRangeAddress(0, 0, 3, 5));

				Cell cellT3 = row3.createCell(7);
				cellT3.setBlank();
				sheet.addMergedRegion(new CellRangeAddress(0, 0, 7, 9));

				Row row4 = sheet.createRow(4);
				Cell cell1 = row4.createCell(0);
				cell1.setBlank();
				sheet.addMergedRegion(new CellRangeAddress(4, 4, 0, 2));

				Cell cell2 = row4.createCell(3);
				cell2.setCellStyle(headerCellStyle);
				cell2.setCellValue("Proposals Details");
				sheet.addMergedRegion(new CellRangeAddress(4, 4, 3, 5));

				Cell cell3 = row4.createCell(6);
				cell3.setBlank();
				sheet.addMergedRegion(new CellRangeAddress(4, 4, 6, 11));

				Row headerRow = sheet.createRow(1);

				for (int col = 0; col < columns.length; col++) {
					Cell cell = headerRow.createCell(col);
					cell.setCellValue(columns[col]);
					cell.setCellStyle(headerCellStyle);
					sheet.setColumnWidth(col, 5000);
				}

				Row row1 = sheet.createRow(2);
				row1.createCell(0).setCellValue(1);
				row1.createCell(1).setCellValue(stateActionPlans.getTotalCost());
				row1.createCell(2).setCellValue(stateActionPlans.getCentralShare());
				row1.createCell(3).setCellValue(stateActionPlans.getStateShare());
				row1.createCell(4).setCellValue(stateActionPlans.getCentralShare());
				Set<SAPAmount> sapAmount = stateActionPlans.getSapAmount();
				for (SAPAmount sap : sapAmount) {

					row1.createCell(5).setCellValue(sap.getFy2022_23());
					row1.createCell(6).setCellValue(sap.getFy2023_24());
					row1.createCell(7).setCellValue(sap.getFy2024_25());
					row1.createCell(8).setCellValue(sap.getTotal());
				}

				Row secondheaderRow = sheet.createRow(5);

				for (int col = 0; col < columns2.length; col++) {
					Cell cell = secondheaderRow.createCell(col);
					cell.setCellValue(columns2[col]);
					cell.setCellStyle(headerCellStyle);
					sheet.setColumnWidth(col, 5000);
				}

				Set<Proposal> proposals = stateActionPlans.getProposals();

				int number = 6;
				int sNo = 1;
				for (Proposal proposal : proposals) {

					Row row = sheet.createRow(number++);
					row.createCell(0).setCellValue(sNo++);
					row.createCell(1).setCellValue(proposal.getStateActionPlanId());
					row.createCell(2).setCellValue(proposal.getUlbName());
					row.createCell(3).setCellValue(proposal.getSector());
					row.createCell(4).setCellValue(proposal.getProposalCost());
					row.createCell(5).setCellValue(proposal.getCentralShare());
					row.createCell(6).setCellValue(proposal.getStateShare());
					row.createCell(7).setCellValue(proposal.getUlbShare());
					row.createCell(8).setCellValue(proposal.getOtherShare());
					row.createCell(9).setCellValue("12-12-2022");
					row.createCell(10).setCellValue(proposal.getStatus());

				}

				workbook.write(output);
				return new ByteArrayInputStream(output.toByteArray());

			}

		} else {

			String[] columns = { "S.No", "Total Cost", "Total Central Share", "Total State Share", "Total Other Share",
					"Amount 2022-23", "Amount 2023-24", "Amount 2024-25", "Total Action Plan Amount" };

			String[] columns2 = { "S.No", "Action Plan Id", "Ulb Name", "Sector", "Total Proposal Cost",
					"Centeral Share", "State Share", "Ulb Share", "Other Share", "Date", "Stauts" };

			try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream output = new ByteArrayOutputStream();) {
				Sheet sheet = workbook.createSheet("Toilet");

				Font headFont = workbook.createFont();
				headFont.setBold(true);
				headFont.setColor(IndexedColors.BLACK.getIndex());

				CellStyle headerCellStyle = workbook.createCellStyle();
				headerCellStyle.setFont(headFont);
				headerCellStyle.setAlignment(HorizontalAlignment.CENTER);

				Row headerRow = sheet.createRow(1);

				for (int col = 0; col < columns.length; col++) {
					Cell cell = headerRow.createCell(col);
					cell.setCellValue(columns[col]);
					cell.setCellStyle(headerCellStyle);
					sheet.setColumnWidth(col, 5000);
				}

				Row row3 = sheet.createRow(0);
				Cell cellT1 = row3.createCell(0);
				cellT1.setBlank();
				sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 2));

				Cell cellT2 = row3.createCell(3);
				cellT2.setCellStyle(headerCellStyle);
				cellT2.setCellValue("State Action Plan Details");
				sheet.addMergedRegion(new CellRangeAddress(0, 0, 3, 5));

				Cell cellT3 = row3.createCell(7);
				cellT3.setBlank();
				sheet.addMergedRegion(new CellRangeAddress(0, 0, 7, 9));

				Row row4 = sheet.createRow(4);
				Cell cell1 = row4.createCell(0);
				cell1.setBlank();
				sheet.addMergedRegion(new CellRangeAddress(4, 4, 0, 2));

				Cell cell2 = row4.createCell(3);
				cell2.setCellStyle(headerCellStyle);
				cell2.setCellValue("Proposals Details");
				sheet.addMergedRegion(new CellRangeAddress(4, 4, 3, 5));

				Cell cell3 = row4.createCell(6);
				cell3.setBlank();
				sheet.addMergedRegion(new CellRangeAddress(4, 4, 7, 11));

				Row row1 = sheet.createRow(2);
				row1.createCell(0).setCellValue(1);
				row1.createCell(1).setCellValue(stateActionPlans.getTotalCost());
				row1.createCell(2).setCellValue(stateActionPlans.getCentralShare());
				row1.createCell(3).setCellValue(stateActionPlans.getStateShare());
				row1.createCell(4).setCellValue(stateActionPlans.getCentralShare());

				Set<SAPAmount> sapAmount = stateActionPlans.getSapAmount();
				for (SAPAmount sap : sapAmount) {

					row1.createCell(5).setCellValue(sap.getFy2022_23());
					row1.createCell(6).setCellValue(sap.getFy2023_24());
					row1.createCell(7).setCellValue(sap.getFy2024_25());
					row1.createCell(8).setCellValue(sap.getTotal());
				}

				Row secondheaderRow = sheet.createRow(6);

				for (int col = 0; col < columns2.length; col++) {
					Cell cell = secondheaderRow.createCell(col);
					cell.setCellValue(columns2[col]);
					cell.setCellStyle(headerCellStyle);
					sheet.setColumnWidth(col, 5000);
				}

				Set<Proposal> proposals = stateActionPlans.getProposals();

				int number = 7;
				int sNo = 1;
				for (Proposal proposal : proposals) {

					Row row = sheet.createRow(number++);
					row.createCell(0).setCellValue(sNo++);
					row.createCell(1).setCellValue(proposal.getStateActionPlanId());
					row.createCell(2).setCellValue(proposal.getUlbName());
					row.createCell(3).setCellValue(proposal.getSector());
					row.createCell(4).setCellValue(proposal.getProposalCost());
					row.createCell(5).setCellValue(proposal.getCentralShare());
					row.createCell(6).setCellValue(proposal.getStateShare());
					row.createCell(7).setCellValue(proposal.getUlbShare());
					row.createCell(8).setCellValue(proposal.getOtherShare());
					row.createCell(9).setCellValue("12-12-2022");
					row.createCell(10).setCellValue(proposal.getStatus());

				}
				workbook.write(output);
				return new ByteArrayInputStream(output.toByteArray());

			}

		}
	}

	public static ByteArrayInputStream excelGeneratorForIEC(StateActionPlan stateActionPlans) throws IOException {

		String[] columns = { "S.No", "Total Cost", "Total Central Share", "Total State Share", "Total Other Share",
				"Amount 2022-23", "Amount 2023-24", "Amount 2024-25", "Total Action Plan Amount" };

		String[] columns2 = { "S.No", "Action Plan Id", "Ulb Name", "Sector", "Total Proposal Cost", "Centeral Share",
				"State Share", "Ulb Share", "Other Share", "Date", "Stauts" };

		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream output = new ByteArrayOutputStream();) {
			Sheet sheet = workbook.createSheet("IEC");

			Font headFont = workbook.createFont();
			headFont.setBold(true);
			headFont.setColor(IndexedColors.BLACK.getIndex());

			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headFont);
			headerCellStyle.setAlignment(HorizontalAlignment.CENTER);

			Row row3 = sheet.createRow(0);
			Cell cellT1 = row3.createCell(0);
			cellT1.setBlank();
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 2));

			Cell cellT2 = row3.createCell(3);
			cellT2.setCellStyle(headerCellStyle);
			cellT2.setCellValue("State Action Plan Details");
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 3, 5));

			Cell cellT3 = row3.createCell(7);
			cellT3.setBlank();
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 7, 9));

			Row row4 = sheet.createRow(4);
			Cell cell1 = row4.createCell(0);
			cell1.setBlank();
			sheet.addMergedRegion(new CellRangeAddress(4, 4, 0, 2));

			Cell cell2 = row4.createCell(3);
			cell2.setCellStyle(headerCellStyle);
			cell2.setCellValue("Proposals Details");
			sheet.addMergedRegion(new CellRangeAddress(4, 4, 3, 5));

			Cell cell3 = row4.createCell(6);
			cell3.setBlank();
			sheet.addMergedRegion(new CellRangeAddress(4, 4, 6, 11));

			Row headerRow = sheet.createRow(1);

			for (int col = 0; col < columns.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(columns[col]);
				cell.setCellStyle(headerCellStyle);
				sheet.setColumnWidth(col, 5000);
			}

			Row row1 = sheet.createRow(2);
			row1.createCell(0).setCellValue(1);
			row1.createCell(1).setCellValue(stateActionPlans.getTotalCost());
			row1.createCell(2).setCellValue(stateActionPlans.getCentralShare());
			row1.createCell(3).setCellValue(stateActionPlans.getStateShare());
			row1.createCell(4).setCellValue(stateActionPlans.getCentralShare());
			Set<SAPAmount> sapAmount = stateActionPlans.getSapAmount();
			for (SAPAmount sap : sapAmount) {

				row1.createCell(5).setCellValue(sap.getFy2022_23());
				row1.createCell(6).setCellValue(sap.getFy2023_24());
				row1.createCell(7).setCellValue(sap.getFy2024_25());
				row1.createCell(8).setCellValue(sap.getTotal());
			}

			Row secondheaderRow = sheet.createRow(5);

			for (int col = 0; col < columns2.length; col++) {
				Cell cell = secondheaderRow.createCell(col);
				cell.setCellValue(columns2[col]);
				cell.setCellStyle(headerCellStyle);
				sheet.setColumnWidth(col, 5000);
			}

			Set<Proposal> proposals = stateActionPlans.getProposals();

			int number = 6;
			int sNo = 1;
			for (Proposal proposal : proposals) {

				Row row = sheet.createRow(number++);
				row.createCell(0).setCellValue(sNo++);
				row.createCell(1).setCellValue(proposal.getStateActionPlanId());
				row.createCell(2).setCellValue(proposal.getUlbName());
				row.createCell(3).setCellValue(proposal.getSector());
				row.createCell(4).setCellValue(proposal.getProposalCost());
				row.createCell(5).setCellValue(proposal.getCentralShare());
				row.createCell(6).setCellValue(proposal.getStateShare());
				row.createCell(7).setCellValue(proposal.getUlbShare());
				row.createCell(8).setCellValue(proposal.getOtherShare());
				row.createCell(9).setCellValue("12-12-2022");
				row.createCell(10).setCellValue(proposal.getStatus());

			}

			workbook.write(output);
			return new ByteArrayInputStream(output.toByteArray());

		}

	}

	public static ByteArrayInputStream excelGeneratorForCB(StateActionPlan stateActionPlans) throws IOException {

		String[] columns = { "S.No", "Total Cost", "Total Central Share", "Total State Share", "Total Other Share",
				"Amount 2022-23", "Amount 2023-24", "Amount 2024-25", "Total Action Plan Amount" };

		String[] columns2 = { "S.No", "Action Plan Id", "Ulb Name", "Sector", "Total Proposal Cost", "Centeral Share",
				"State Share", "Ulb Share", "Other Share", "Date", "Stauts" };

		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream output = new ByteArrayOutputStream();) {
			Sheet sheet = workbook.createSheet("Capasity Building");

			Font headFont = workbook.createFont();
			headFont.setBold(true);
			headFont.setColor(IndexedColors.BLACK.getIndex());

			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headFont);
			headerCellStyle.setAlignment(HorizontalAlignment.CENTER);

			Row row3 = sheet.createRow(0);
			Cell cellT1 = row3.createCell(0);
			cellT1.setBlank();
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 2));

			Cell cellT2 = row3.createCell(3);
			cellT2.setCellStyle(headerCellStyle);
			cellT2.setCellValue("State Action Plan Details");
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 3, 5));

			Cell cellT3 = row3.createCell(7);
			cellT3.setBlank();
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 7, 9));

			Row row4 = sheet.createRow(4);
			Cell cell1 = row4.createCell(0);
			cell1.setBlank();
			sheet.addMergedRegion(new CellRangeAddress(4, 4, 0, 2));

			Cell cell2 = row4.createCell(3);
			cell2.setCellStyle(headerCellStyle);
			cell2.setCellValue("Proposals Details");
			sheet.addMergedRegion(new CellRangeAddress(4, 4, 3, 5));

			Cell cell3 = row4.createCell(6);
			cell3.setBlank();
			sheet.addMergedRegion(new CellRangeAddress(4, 4, 6, 11));

			Row headerRow = sheet.createRow(1);

			for (int col = 0; col < columns.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(columns[col]);
				cell.setCellStyle(headerCellStyle);
				sheet.setColumnWidth(col, 5000);
			}

			Row row1 = sheet.createRow(2);
			row1.createCell(0).setCellValue(1);
			row1.createCell(1).setCellValue(stateActionPlans.getTotalCost());
			row1.createCell(2).setCellValue(stateActionPlans.getCentralShare());
			row1.createCell(3).setCellValue(stateActionPlans.getStateShare());
			row1.createCell(4).setCellValue(stateActionPlans.getCentralShare());
			Set<SAPAmount> sapAmount = stateActionPlans.getSapAmount();
			for (SAPAmount sap : sapAmount) {

				row1.createCell(5).setCellValue(sap.getFy2022_23());
				row1.createCell(6).setCellValue(sap.getFy2023_24());
				row1.createCell(7).setCellValue(sap.getFy2024_25());
				row1.createCell(8).setCellValue(sap.getTotal());
			}

			Row secondheaderRow = sheet.createRow(5);

			for (int col = 0; col < columns2.length; col++) {
				Cell cell = secondheaderRow.createCell(col);
				cell.setCellValue(columns2[col]);
				cell.setCellStyle(headerCellStyle);
				sheet.setColumnWidth(col, 5000);
			}

			Set<Proposal> proposals = stateActionPlans.getProposals();

			int number = 6;
			int sNo = 1;
			for (Proposal proposal : proposals) {

				Row row = sheet.createRow(number++);
				row.createCell(0).setCellValue(sNo++);
				row.createCell(1).setCellValue(proposal.getStateActionPlanId());
				row.createCell(2).setCellValue(proposal.getUlbName());
				row.createCell(3).setCellValue(proposal.getSector());
				row.createCell(4).setCellValue(proposal.getProposalCost());
				row.createCell(5).setCellValue(proposal.getCentralShare());
				row.createCell(6).setCellValue(proposal.getStateShare());
				row.createCell(7).setCellValue(proposal.getUlbShare());
				row.createCell(8).setCellValue(proposal.getOtherShare());
				row.createCell(9).setCellValue("12-12-2022");
				row.createCell(10).setCellValue(proposal.getStatus());

			}

			workbook.write(output);
			return new ByteArrayInputStream(output.toByteArray());

		}

	}

}
