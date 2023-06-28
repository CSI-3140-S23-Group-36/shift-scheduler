package shift.scheduler.app.codegen;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Generates a DRL file containing business rules for shift generation.
 */
public class DrlFileGenerator {

    private static BufferedWriter writer;

    public static void main(String[] args) {

        if (args.length != 2) {
            System.out.println("Usage: java DrlFileGenerator <max number of employees per day> <rule file path>");
            System.exit(1);
        }

        int numDailyScheduleRules = Integer.parseInt(args[0]);
        String filePath = args[1];

        try {
            writer = new BufferedWriter(new FileWriter(filePath));

            writeImports();
            writeLine("");

            for (int i = 1; i <= numDailyScheduleRules; i++)
                writeDailyScheduleRule(i);

            writeEmptyScheduleRule();
            writeScheduleForWeekRule();
            writeWeeklySchedulesQuery();

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static void writeLine(String line) throws IOException {
        writer.write(line + System.lineSeparator());
    }

    private static void writeImports() throws IOException {

        writeLine("import shift.scheduler.app.models.Hour;");
        writeLine("import shift.scheduler.app.models.Day;");
        writeLine("import shift.scheduler.app.models.TimePeriod;");
        writeLine("import shift.scheduler.app.models.ScheduleRequirements;");
        writeLine("import shift.scheduler.app.models.Employee;");
        writeLine("import shift.scheduler.app.models.ScheduleForDay;");
        writeLine("import shift.scheduler.app.models.ScheduleForWeek;");
        writeLine("import java.util.List;");
        writeLine("import java.util.ArrayList;");
    }

    private static void writeDailyScheduleRule(int numEmployees) throws IOException {

        writeLine(String.format("rule \"Schedule for a day with %d employees\"", numEmployees));
        writeLine("when");
        writeLine("    ScheduleRequirements( $numEmps : numberOfEmployeesPerHour, $day : day, $numHours : numHours, $hours : hours )");
        writeLine("");
        writeLine("    // Generate at most three possible schedules for each day");
        writeLine("    Number( intValue < 3 ) from accumulate( ScheduleForDay( day == $day ),");
        writeLine("                                            init( int count = 0; ),");
        writeLine("                                            action( count++; ),");
        writeLine("                                            result( new Integer(count) ) )");
        writeLine("");

        for (int i = 1; i <= numEmployees; i++) {
            writer.write(String.format("    $emp%d : Employee( ", i));

            for (int j = 1; j < i; j++) {
                writer.write(String.format("this != $emp%d", j));

                if (j != (i - 1))
                    writer.write(", ");
            }

            writeLine(" )");
        }

        writeLine("");

        for (int i = 1; i <= numEmployees; i++)
            writeLine(String.format("    $shift%d : TimePeriod( day == $day, employee == $emp%d )", i, i));

        writer.write("    $shifts : List() from collect( TimePeriod( ");

        for (int i = 1; i <= numEmployees; i++) {
            writer.write(String.format("(this == $shift%d)", i));

            if (i != numEmployees)
                writer.write(" || ");
        }

        writeLine(" ) )");
        writeLine("");
        writeLine("    forall( Hour( $value : value ) from $hours");
        writeLine("            $numShifts : Number( intValue >= $numEmps ) from accumulate( TimePeriod( (startHour <= $value) && (endHour >= $value) ) from $shifts,");
        writeLine("                                                                         init( int count = 0; ),");
        writeLine("                                                                         action( count++; ),");
        writeLine("                                                                         result( new Integer(count) ) ) )");
        writeLine("then");
        writeLine("    System.out.println(\"Schedule for \" + $day + \":\");");

        for (int i = 1; i <= numEmployees; i++) {
            writeLine(String.format(
                    "    System.out.println(\"Shift %d: \" + $shift%d.getStartHour() + \"-\" + $shift%d.getEndHour());", i, i, i)
            );
        }

        writeLine("");
        writeLine("    insert(new ScheduleForDay($day, $shifts));");
        writeLine("end");
        writeLine("");
    }

    private static void writeEmptyScheduleRule() throws IOException {

        writeLine("rule \"Empty schedule for a non-operational day\"");
        writeLine("when");
        writeLine("    ScheduleRequirements( $day : day, (numberOfEmployeesPerHour == 0) )");
        writeLine("then");
        writeLine("    insert(new ScheduleForDay($day, null));");
        writeLine("end");
        writeLine("");
    }

    private static void writeScheduleForWeekRule() throws IOException {

        writeLine("rule \"Schedule for a week\"");
        writeLine("when");
        writeLine("    Number( intValue < 3 ) from accumulate( ScheduleForWeek(),");
        writeLine("                                            init( int count = 0; ),");
        writeLine("                                            action( count++; ),");
        writeLine("                                            result( new Integer(count) ) )");
        writeLine("");
        writeLine("    $mon : ScheduleForDay( day == Day.MON )");
        writeLine("    $tue : ScheduleForDay( day == Day.TUE )");
        writeLine("    $wed : ScheduleForDay( day == Day.WED )");
        writeLine("    $thu : ScheduleForDay( day == Day.THU )");
        writeLine("    $fri : ScheduleForDay( day == Day.FRI )");
        writeLine("    $sat : ScheduleForDay( day == Day.SAT )");
        writeLine("    $sun : ScheduleForDay( day == Day.SUN )");
        writeLine("then");
        writeLine("    System.out.println(\"Generated weekly schedule\");");
        writeLine("    List<ScheduleForDay> dailySchedules = new ArrayList<>();");
        writeLine("    dailySchedules.add($mon);");
        writeLine("    dailySchedules.add($tue);");
        writeLine("    dailySchedules.add($wed);");
        writeLine("    dailySchedules.add($thu);");
        writeLine("    dailySchedules.add($fri);");
        writeLine("    dailySchedules.add($sat);");
        writeLine("    dailySchedules.add($sun);");
        writeLine("    insert(new ScheduleForWeek(null, dailySchedules));");
        writeLine("end");
        writeLine("");
    }

    private static void writeWeeklySchedulesQuery() throws IOException {

        writeLine("query \"getWeeklySchedules\"");
        writeLine("    $schedule : ScheduleForWeek()");
        writeLine("end");
    }
}
