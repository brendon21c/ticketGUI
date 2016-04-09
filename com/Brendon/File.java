package com.Brendon;


import java.io.*;
import java.util.Vector;

/*
I took this class from your lake runningGUI program and changed it to suit my needs.
I know it's kind of cheating, but it saved me time, sorry.
 */

public class File {

    private static String filename = "Service_Ticket.txt";
    private static String separator = ":";



    static Vector<Ticket> getLakes()  {

        Vector<Ticket> openTickets = new Vector<Ticket>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {

            String ticketData = reader.readLine();

            while (ticketData != null) {

                Ticket t = parseData(ticketData);
                openTickets.add(t);
                ticketData = reader.readLine();
            }

            return openTickets;

        } catch (IOException ioe) {
            System.out.println("Error reading file");
            return new Vector<>();
        }
    }


    static void saveTicket(Vector<Ticket> tickets) {


        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {

            for (Ticket t : tickets) {

                String issue = t.getProblem();
                int idNum = t.getIdNum();
                int severity = t.getSeverity();


                writer.write(idNum + ": " + issue + ": " + severity + "\n");
            }
        }

        catch (IOException ioe) {
            System.out.println("Error writing data");
        }


    }

    static Ticket parseData(String data) {

        //Split the String by the separator
        String[] ticketData = data.split(separator);

        if (ticketData.length == 0) {    // This will happen if there are empty lines in the file, which there shouldn't be...
            return null;
        } else {

            int idNum = Integer.parseInt(ticketData[0]);
            String issue = ticketData[1];
            int severity = Integer.parseInt(ticketData[2].replace(" ",""));

            Ticket ticket = new Ticket(idNum,issue,severity);

            return ticket;
        }
    }


}
