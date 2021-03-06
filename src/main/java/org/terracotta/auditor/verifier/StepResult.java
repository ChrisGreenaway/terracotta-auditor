/*
 * Copyright Terracotta, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.terracotta.auditor.verifier;

import java.util.Set;

/**
 * @author Ludovic Orban
 */
public class StepResult {
  private final long startTs;
  private final long endTs;
  private final Set<RecordValue> possibleValues;
  private final int stepSize;

  public StepResult(long startTs, long endTs, Set<RecordValue> possibleValues, int stepSize) {
    this.startTs = startTs;
    this.endTs = endTs;
    this.possibleValues = possibleValues;
    this.stepSize = stepSize;
  }

  public long getStartTs() {
    return startTs;
  }

  public long getEndTs() {
    return endTs;
  }

  public Set<RecordValue> getPossibleValues() {
    return possibleValues;
  }

  public int getStepSize() {
    return stepSize;
  }
}
