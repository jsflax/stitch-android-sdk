/*
 * Copyright 2018-present MongoDB, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mongodb.stitch.core.services.mongodb.remote.sync;

import com.mongodb.stitch.core.services.mongodb.remote.ChangeEvent;
import com.mongodb.stitch.core.services.mongodb.remote.CompactChangeEvent;

import org.bson.BsonValue;

/**
 * A collection of utility conflict resolvers.
 */
public final class DefaultSyncConflictResolvers {

  /**
   * The remote event will decide the next state of the document in question.
   *
   * @param <T> the type of class represented by the document in the change event.
   * @return the remote full document which may be null.
   */
  public static <T> ConflictHandler<T> remoteWins() {
    return new ConflictHandler<T>() {
      @Override
      public ConflictResolution resolveConflict(
          final BsonValue documentId,
          final ChangeEvent<T> localEvent,
          final CompactChangeEvent<T> remoteEvent
      ) {
        return ConflictResolution.fromRemote();
      }
    };
  }

  /**
   * The local event will decide the next state of the document in question.
   *
   * @param <T> the type of class represented by the document in the change event.
   * @return the local full document which may be null.
   */
  public static <T> ConflictHandler<T> localWins() {
    return new ConflictHandler<T>() {
      @Override
      public ConflictResolution resolveConflict(
          final BsonValue documentId,
          final ChangeEvent<T> localEvent,
          final CompactChangeEvent<T> remoteEvent
      ) {
        return ConflictResolution.fromLocal();
      }
    };
  }
}
