/*
 * Copyright 2006-2018 The MZmine 2 Development Team
 * 
 * This file is part of MZmine 2.
 * 
 * MZmine 2 is free software; you can redistribute it and/or modify it under the terms of the GNU
 * General Public License as published by the Free Software Foundation; either version 2 of the
 * License, or (at your option) any later version.
 * 
 * MZmine 2 is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with MZmine 2; if not,
 * write to the Free Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301
 * USA
 */

package io.github.mzmine.modules.dataprocessing.id_isotopepeakscanner;

import java.util.Collection;
import javax.annotation.Nonnull;

import io.github.mzmine.datamodel.MZmineProject;
import io.github.mzmine.datamodel.PeakList;
import io.github.mzmine.modules.MZmineModuleCategory;
import io.github.mzmine.modules.MZmineProcessingModule;
import io.github.mzmine.parameters.ParameterSet;
import io.github.mzmine.taskcontrol.Task;
import io.github.mzmine.util.ExitCode;
/**
 * 
 * @author Steffen Heuckeroth steffen.heuckeroth@gmx.de / s_heuc03@uni-muenster.de
 *
 */
public class IsotopePeakScannerModule implements MZmineProcessingModule {

  private static final String MODULE_NAME = "Isotope peak scanner";
  private static final String MODULE_DESCRIPTION =
      "Searches for m/z and intensity distribution of a calculated isotope pattern within a feature list.";

  @Override
  public @Nonnull String getName() {
    return MODULE_NAME;
  }

  public @Nonnull MZmineModuleCategory getModuleCategory() {
    return MZmineModuleCategory.ISOTOPES;
  }

  @Override
  public @Nonnull Class<? extends ParameterSet> getParameterSetClass() {
    return IsotopePeakScannerParameters.class;
  }

  public @Nonnull String getDescription() {
    return MODULE_DESCRIPTION;
  }

  @Override
  public @Nonnull ExitCode runModule(@Nonnull MZmineProject project,
      @Nonnull ParameterSet parameters, @Nonnull Collection<Task> tasks) {
    PeakList peakLists[] = parameters.getParameter(IsotopePeakScannerParameters.PEAK_LISTS)
        .getValue().getMatchingPeakLists();

    for (PeakList peakList : peakLists) {
      Task newTask = new IsotopePeakScannerTask(project, peakList, parameters);
      tasks.add(newTask);
    }
    return ExitCode.OK;
  }

}