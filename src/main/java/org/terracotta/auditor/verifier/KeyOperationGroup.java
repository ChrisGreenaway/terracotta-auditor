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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class KeyOperationGroup {

  private final List<KeyOperation> sortedOperations = new ArrayList<>();
  private long startTS;
  private long endTS;

  KeyOperationGroup(KeyOperation operation) {
    this.sortedOperations.add(operation);
    this.startTS = operation.getStartTS();
    this.endTS = operation.getEndTS();
  }

  public List<KeyOperation> getOperations() {
    return sortedOperations;
  }

  public void add(KeyOperation operation) {
    sortedOperations.add(operation);
    sortedOperations.sort(Utils.operationComparator());
    if (operation.getEndTS() > endTS) {
      endTS = operation.getEndTS();
    }
    if (operation.getStartTS() < startTS) {
      startTS = operation.getStartTS();
    }
  }

  Values replay(RecordValue fromValue) {
    return new OrderDeterminer(sortedOperations).findPossibleOutcomes(fromValue);
  }

  int size() {
    return sortedOperations.size();
  }

  long endTS() {
    return endTS;
  }

  long startTS() {
    return startTS;
  }

  @Override
  public String toString() {
    return sortedOperations.toString();
  }
}
