package com.excel.config;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.excel.entity.Proposal;

@Service
public class ExcelGeneratorConfig {

	public static ByteArrayInputStream excelGenerator(List<Proposal> proposals) throws IOException {

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

			CellStyle headerCellStyle1 = workbook.createCellStyle();
			headerCellStyle1.setFont(headFont1);

			Row row = sheet.createRow(0);

			Cell cell2 = row.createCell(0);
			cell2.setBlank();
			sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 10));

			Cell cell3 = row.createCell(11);
			cell3.setCellValue("Cesspool tankers presently available");
			sheet.addMergedRegion(new CellRangeAddress(0, 1, 11, 13));

			Cell cell4 = row.createCell(14);
			cell4.setCellValue("Cesspool tankers requirement 2025");
			sheet.addMergedRegion(new CellRangeAddress(0, 1, 14, 16));

			Cell cell5 = row.createCell(17);
			cell5.setBlank();
			sheet.addMergedRegion(new CellRangeAddress(0, 1, 17, 18));

			Cell cell6 = row.createCell(19);
			cell6.setCellValue("Cesspool tankers proposed");
			sheet.addMergedRegion(new CellRangeAddress(0, 1, 19, 20));

			Cell cell7 = row.createCell(21);
			cell7.setCellValue("Any other cleaning equipment proposed");
			sheet.addMergedRegion(new CellRangeAddress(0, 1, 21, 22));

			Cell cell8 = row.createCell(23);
			cell8.setCellValue("I&D Infrastructure");
			sheet.addMergedRegion(new CellRangeAddress(0, 1, 23, 32));

			Cell cell9 = row.createCell(33);
			cell9.setCellValue("Septage receiving facility proposed (KLD)");
			sheet.addMergedRegion(new CellRangeAddress(0, 1, 33, 34));

			Cell cell10 = row.createCell(35);
			cell10.setCellValue("Treatment Systems");
			sheet.addMergedRegion(new CellRangeAddress(0, 1, 35, 38));

			Row headerRow = sheet.createRow(2);
			Row headerRow1 = sheet1.createRow(2);

			for (int col = 0; col < columns.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(columns[col]);
				cell.setCellStyle(headerCellStyle);
			}

			for (int col = 0; col < columns2.length; col++) {
				Cell cell = headerRow1.createCell(col);
				cell.setCellValue(columns2[col]);
				cell.setCellStyle(headerCellStyle1);
			}

			int rowInd = 2;
			int sNo = 1;
			for (Proposal proposal : proposals) {

				Row row1 = sheet.createRow(rowInd++);
				row1.createCell(0).setCellValue(sNo++);
				row1.createCell(1).setCellValue(proposal.getSector());
				row1.createCell(2).setCellValue(proposal.getPresentPopulation());
				row1.createCell(3).setCellValue(proposal.getPresentHouseHold());
				row1.createCell(4).setCellValue(proposal.getPopulationOf2011());
				row1.createCell(5).setCellValue(proposal.getPresentHouseHold());
				row1.createCell(6).setCellValue(proposal.getProjectedPopulation2025());
				row1.createCell(7).setCellValue(proposal.getProjectedHousehold2025());
				row1.createCell(8).setCellValue(proposal.getAmountInLakh());
				row1.createCell(9).setCellValue(proposal.getCapacity());
				row1.createCell(10).setCellValue(proposal.getCentralShare());
				row1.createCell(11).setCellValue(proposal.getCityOrUlb());
				row1.createCell(12).setCellValue(proposal.getDiaAndMaterialOfPipe());
				row1.createCell(13).setCellValue(proposal.getDistrictName());
				row1.createCell(14).setCellValue(proposal.getLength75CM());
				row1.createCell(15).setCellValue(proposal.getLengthOfPipe());
				row1.createCell(16).setCellValue(proposal.getNumberOfPumpingStations());
				row1.createCell(17).setCellValue(proposal.getNumberOfSTPProposed());
				row1.createCell(18).setCellValue(proposal.getPrivateTotalFSTP());
				row1.createCell(19).setCellValue(proposal.getUlbCode());
				row1.createCell(20).setCellValue(proposal.getStateId());
				row1.createCell(21).setCellValue(proposal.getProposalId());
				row1.createCell(22).setCellValue(proposal.getDistrictId());
				row1.createCell(23).setCellValue(proposal.getUniqueProposalId());
				row1.createCell(24).setCellValue(proposal.getStateName());
				row1.createCell(25).setCellValue(proposal.getSectorName());
				row1.createCell(26).setCellValue(proposal.getAmountInLakh());
				row1.createCell(27).setCellValue(proposal.getPresentHouseHold());
				row1.createCell(28).setCellValue(proposal.getStateId());
				row1.createCell(29).setCellValue(proposal.getUlbName());
				row1.createCell(30).setCellValue(proposal.getState());
				row1.createCell(31).setCellValue(proposal.getDiaAndMaterialOfPipe());
				row1.createCell(32).setCellValue(proposal.getNumberOfSTPProposed());
				row1.createCell(33).setCellValue(proposal.getNumberOfPumpingStations());
				row1.createCell(34).setCellValue(proposal.getStateId());
				row1.createCell(35).setCellValue(proposal.getNumberOfSTPProposed());
				row1.createCell(36).setCellValue(proposal.getStateName());
				row1.createCell(37).setCellValue(proposal.getDistrictName());
				row1.createCell(38).setCellValue(proposal.getNumberOfSTPProposed());
				row1.createCell(39).setCellValue(proposal.getSectorName());
				row1.createCell(40).setCellValue(proposal.getNumberOfSTPProposed());
				row1.createCell(41).setCellValue(proposal.getStateName());
				row1.createCell(42).setCellValue(proposal.getDistrictName());
				row1.createCell(43).setCellValue(proposal.getNumberOfSTPProposed());
				row1.createCell(44).setCellValue(proposal.getSectorName());

			}

			int rowInd1 = 2;
			int sNo1 = 1;
			for (Proposal proposal : proposals) {

				Row row2 = sheet.createRow(rowInd1++);
				row2.createCell(0).setCellValue(sNo1++);
				row2.createCell(1).setCellValue(proposal.getSector());
				row2.createCell(2).setCellValue(proposal.getPresentPopulation());
				row2.createCell(3).setCellValue(proposal.getPresentHouseHold());
				row2.createCell(4).setCellValue(proposal.getPopulationOf2011());
				row2.createCell(5).setCellValue(proposal.getPresentHouseHold());
				row2.createCell(6).setCellValue(proposal.getProjectedPopulation2025());
				row2.createCell(7).setCellValue(proposal.getProjectedHousehold2025());
				row2.createCell(8).setCellValue(proposal.getAmountInLakh());
				row2.createCell(9).setCellValue(proposal.getCapacity());
				row2.createCell(10).setCellValue(proposal.getCentralShare());
				row2.createCell(11).setCellValue(proposal.getCityOrUlb());
				row2.createCell(12).setCellValue(proposal.getDiaAndMaterialOfPipe());
				row2.createCell(13).setCellValue(proposal.getDistrictName());
				row2.createCell(14).setCellValue(proposal.getLength75CM());
				row2.createCell(15).setCellValue(proposal.getLengthOfPipe());
				row2.createCell(16).setCellValue(proposal.getNumberOfPumpingStations());
				row2.createCell(17).setCellValue(proposal.getNumberOfSTPProposed());
				row2.createCell(18).setCellValue(proposal.getPrivateTotalFSTP());
				row2.createCell(19).setCellValue(proposal.getUlbCode());
				row2.createCell(20).setCellValue(proposal.getStateId());
				row2.createCell(21).setCellValue(proposal.getProposalId());
				row2.createCell(22).setCellValue(proposal.getDistrictId());
				row2.createCell(23).setCellValue(proposal.getUniqueProposalId());
				row2.createCell(24).setCellValue(proposal.getStateName());
				row2.createCell(25).setCellValue(proposal.getSectorName());
				row2.createCell(26).setCellValue(proposal.getAmountInLakh());
				row2.createCell(27).setCellValue(proposal.getPresentHouseHold());
				row2.createCell(28).setCellValue(proposal.getStateId());
				row2.createCell(29).setCellValue(proposal.getUlbName());
				row2.createCell(30).setCellValue(proposal.getState());
				row2.createCell(31).setCellValue(proposal.getDiaAndMaterialOfPipe());
				row2.createCell(32).setCellValue(proposal.getNumberOfSTPProposed());

			}
			workbook.write(output);
			return new ByteArrayInputStream(output.toByteArray());

		}
	}

}
