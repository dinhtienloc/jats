///*
// * Copyright 2017 Greg Whitaker
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *      http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//
//package vn.locdt.jats.module.shell.provider;
//
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
///**
// * Provider responsible for defining the shell banner that is printed on startup.
// */
//@Component
//@Order(Ordered.HIGHEST_PRECEDENCE)
//public class AppBannerProvider implements BannerProvider {
//
//    public String getBanner() {
//        StringBuffer buf = new StringBuffer();
//        buf.append( "\n" +
//                    "       ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\n" +
//                    "       ░░     ██  ██████  ██████  ██████░░\n" +
//                    "       ░░     ██  ██  ██    ██   ██     ░░\n" +
//                    "       ░░     ██  ██████    ██    █████ ░░\n" +
//                    "       ░░██   ██  ██  ██    ██        ██░░\n" +
//                    "       ░░ █████   ██  ██    ██   ██████ ░░\n" +
//                    "       ░░                               ░░\n" +
//                    "       ░░ -----* Welcome to JATS *----- ░░\n" +
//                    "       ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\n");
////        buf.append(OsUtils.LINE_SEPARATOR);
////        buf.append("▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄").append(OsUtils.LINE_SEPARATOR);
////        buf.append("                _   _    ____ _____").append(OsUtils.LINE_SEPARATOR);
////        buf.append("               | | / \\  / ___|_   _|").append(OsUtils.LINE_SEPARATOR);
////        buf.append("            _  | |/ _ \\ \\\\___  | |").append(OsUtils.LINE_SEPARATOR);
////        buf.append("           | |_| / ___ \\ ___)| | |").append(OsUtils.LINE_SEPARATOR);
////        buf.append("            \\___/_/   \\_|____/ |_|").append(OsUtils.LINE_SEPARATOR);
//        buf.append(OsUtils.LINE_SEPARATOR);
//        buf.append("    (For assistance type \"help\" then hit ENTER)").append(OsUtils.LINE_SEPARATOR);
//        return buf.toString();
//
//    }
//
//    @Override
//    public String getVersion() {
//        return VersionUtils.versionInfo();
//    }
//
//    @Override
//    public String getProviderName() {
//        return "JATS";
//    }
//
//    @Override
//    public String getWelcomeMessage() {
//        return null;
//    }
//
//}
