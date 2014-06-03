/**
 * Axelor Business Solutions
 *
 * Copyright (C) 2012-2014 Axelor (<http://axelor.com>).
 *
 * This program is free software: you can redistribute it and/or  modify
 * it under the terms of the GNU Affero General Public License, version 3,
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.axelor.apps.base.service;

import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.axelor.apps.base.db.Company;
import com.axelor.apps.base.db.Period;
import com.axelor.exception.AxelorException;
import com.axelor.exception.db.IException;

public class PeriodService {
	
	private static final Logger LOG = LoggerFactory.getLogger(PeriodService.class); 
	
	/**
	 * Recupère la bonne période pour la date passée en paramètre
	 * @param date
	 * @param company
	 * @return
	 * @throws AxelorException 
	 */
	public Period rightPeriod(LocalDate date, Company company) throws AxelorException {
	
		Period period = Period.all().filter("company = ?1 and fromDate <= ?2 and toDate >= ?3",company,date,date).fetchOne();
		if (period == null || period.getStatus().getCode().equals("clo"))  {
			throw new AxelorException(String.format("Aucune période trouvée ou celle-ci clôturée pour la société %s", company.getName()), IException.CONFIGURATION_ERROR);
		}
		else  {
			LOG.debug("Period : {}",period);	
			return period;
		}
		
	}
	
}
