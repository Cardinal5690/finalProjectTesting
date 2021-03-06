package com.testing.controller.command;

import com.testing.controller.command.other.admin.*;
import com.testing.controller.command.other.login.LoginCommand;
import com.testing.controller.command.other.login.LogoutCommand;
import com.testing.controller.command.other.MainCommand;
import com.testing.controller.command.other.PageErrorCommand;
import com.testing.controller.command.other.registration.RegistrationCommand;
import com.testing.controller.command.other.registration.RegistrationCommandPage;
import com.testing.controller.command.other.student.*;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static final Logger LOGGER = Logger.getLogger(CommandFactory.class);
    private static Map<String, Command> commandMap = new HashMap<>();

    static {
        commandMap.put("main", new MainCommand());
        commandMap.put("login", new LoginCommand());
        commandMap.put("logout",new LogoutCommand());
        commandMap.put("registration", new RegistrationCommandPage());
        commandMap.put("registration/create", new RegistrationCommand());
        commandMap.put("student",new StudentCommandPage());
        commandMap.put("student/subject", new SubjectPage());
        commandMap.put("student/subject/test", new TestCommandPage());
        commandMap.put("student/subject/test/pass", new PassCommandPage());
        commandMap.put("student/subject/test/pass/result", new ResultCommand());
        commandMap.put("admin", new AdminCommandPage());
        commandMap.put("admin/subject", new AdminSubjectCommand());
        commandMap.put("admin/user", new AdminUpdateCommandPage());
        commandMap.put("admin/user/update", new AdminUpdateUserCommand());
        commandMap.put("admin/history", new AdminHistoryOfResultsCommand());
        commandMap.put("admin/subject/test", new AdminTestCommand());
        commandMap.put("admin/subject/test/create/action", new AdminTestCreateCommand());
        commandMap.put("admin/subject/test/create", new AdminTestCreateCommandPage());
        commandMap.put("admin/subject/test/question", new AdminQuestionCreateCommandPage());
        commandMap.put("admin/subject/test/question/create", new AdminQuestionCreateCommand());
        commandMap.put("admin/subject/test/delete", new AdminTestDeleteCommand());
    }

    public static Command getCommand(String url) {
        LOGGER.info("Command get");
        Command command = commandMap.get(url);
        if (command == null) {
            return new PageErrorCommand();
        }
        return command;
    }
}
