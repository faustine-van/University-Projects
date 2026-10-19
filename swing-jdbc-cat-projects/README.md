# Java Swing and JDBC Projects

Seven Java exercises: an abstract class, a calendar, a banking loan screen,
a scientific calculator, a student transcript with PDF export, an add-numbers
screen, and a JDBC grade report. Swing screens with MySQL behind them.

| Package | Question | Run |
|---|---|---|
| com.cat | Q1 abstract class | your Q1 main class |
| com.calendar | Q2 year calendar | YearCalendarFrame |
| com.bankingsystem | Q3 loan | BankingFrame |
| com.calculator | Q4 scientific calculator | ScientificCalculator |
| com.transcriptsystem | Q5 transcript + PDF | TranscriptFrame (console: TranscriptConsole) |
| com.addnumbers | Q6 add numbers | AddNumbers |
| com.jdbc | Q7 grade report (also shared database code) | GradeReportFrame (console: GradeReportConsole) |

## Setup

1. JDK 17+ and Eclipse with WindowBuilder.
2. MySQL: run `sql/transcript_system.sql`. It drops and recreates the
   `transcript_system` database, so re-running resets the data.
3. Download MySQL Connector/J and iText 5 (itextpdf-5.5.13.x) into `lib/`
   and add both to the build path.
4. Set the user and password in `com.jdbc.DBConnection`.