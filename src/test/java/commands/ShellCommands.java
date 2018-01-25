/*
 * Copyright 2017 Greg Whitaker
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package commands;

import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliAvailabilityIndicator;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;

/**
 * Defines commands supported by the shell.
 */
@Component
public class ShellCommands implements CommandMarker {

    @CliAvailabilityIndicator({ "echo" })
    public boolean isEchoAvailable() {
        return true;
    }

    @CliCommand(value = "echo", help = "echos the message")
    public String echo(@CliOption(key = { "message" }, mandatory = true, help = "message to echo") final String message) {
        return "Echo: " + message;
    }
}
