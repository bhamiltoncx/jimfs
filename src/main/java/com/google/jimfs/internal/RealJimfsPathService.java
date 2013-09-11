/*
 * Copyright 2013 Google Inc.
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

package com.google.jimfs.internal;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import com.google.jimfs.path.PathType;

import javax.annotation.Nullable;

/**
 * Real implementation of {@link PathService}.
 *
 * @author Colin Decker
 */
final class RealJimfsPathService extends PathService {

  private volatile JimfsFileSystem fileSystem;

  RealJimfsPathService(PathType type) {
    super(type);
  }

  /**
   * Allow the file system to be set after the path service is created.
   */
  void setFileSystem(JimfsFileSystem fileSystem) {
    checkState(this.fileSystem == null, "may not set fileSystem twice");
    this.fileSystem = checkNotNull(fileSystem);
  }

  /**
   * Returns the file system this service is for.
   */
  public JimfsFileSystem getFileSystem() {
    return fileSystem;
  }

  @Override
  public JimfsPath createPathInternal(@Nullable Name root, Iterable<Name> names) {
    return new RealJimfsPath(this, root, names);
  }
}
