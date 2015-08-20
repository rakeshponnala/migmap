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


package com.antigenomics.higblast.io

import com.antigenomics.higblast.mapping.ReadMapping

class ReadMappingOutput implements InputPort<ReadMapping> {
    final PlainTextOutput plainTextOutput

    ReadMappingOutput(PlainTextOutput plainTextOutput = new StdOutput()) {
        this.plainTextOutput = plainTextOutput
        plainTextOutput.println(ReadMapping.OUTPUT_HEADER)
    }

    @Override
    void put(ReadMapping readMapping) {
        if (readMapping.mapped) {
            plainTextOutput.put(readMapping.toString())
        }
    }

    @Override
    void close() {
        plainTextOutput.close()
    }
}
