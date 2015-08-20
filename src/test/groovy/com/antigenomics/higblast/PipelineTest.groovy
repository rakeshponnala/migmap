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

import com.antigenomics.higblast.blast.BlastInstanceFactory
import com.antigenomics.higblast.genomic.SegmentDatabase
import com.antigenomics.higblast.io.ClonotypeOutput
import com.antigenomics.higblast.io.FastqReader
import com.antigenomics.higblast.io.InputPortMerge
import com.antigenomics.higblast.io.ReadMappingOutput
import org.junit.AfterClass
import org.junit.Test

class PipelineTest {
    @Test
    void sampleTest() {
        def reader = new FastqReader("sample.fastq.gz", true)
        def factory = new BlastInstanceFactory("data/", "human", ["IGH"], true, false)

        def pipeline = new Pipeline(reader, factory,
                new InputPortMerge(new ReadMappingOutput(), new ClonotypeOutput()))

        pipeline.run()

        assert pipeline.inputCount == 1000
        assert pipeline.mappedRatio > 0.95
        assert pipeline.cdr3FoundRatio > 0.90
    }

    @AfterClass
    static void tearDown() {
        SegmentDatabase.clearTemporaryFiles()
    }


}
