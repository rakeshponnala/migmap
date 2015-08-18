/*
 * Copyright 2013-2015 Mikhail Shugay (mikhail.shugay@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.antigenomics.higblast

class RuntimeInfo {
    static final boolean WIN = System.properties['os.name'].toLowerCase().contains('windows')
    static int N_THREADS = Runtime.runtime.availableProcessors()
    static String BLAST_HOME = ""

    static String wrapCommand(String command) {
        "${BLAST_HOME.length() > 0 ? "$BLAST_HOME/" : ""}$command${WIN ? ".exe" : ""}"
    }

    static String getMakeDb() {
        wrapCommand("makeblastdb")
    }

    static String getIgBlast() {
        wrapCommand("igblastn")
    }

    static boolean check() {
        try {
            makeDb.execute()
            igBlast.execute()
        } catch (Exception e) {
            Util.report("[ERROR] Unable to run IGBLAST binaries. Error:\n${e.toString()}", 1)
            return false
        }

        true
    }
}
