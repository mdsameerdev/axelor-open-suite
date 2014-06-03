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

import com.axelor.apps.base.db.Partner;
import com.google.common.base.Strings;


public class PartnerService {
	
	
	public void setPartnerFullName(Partner partner)  {
		
		partner.setFullName(this.computeFullName(partner));
		
	}
	
	public String computeFullName(Partner partner)  {
		if(!Strings.isNullOrEmpty(partner.getName()) && !Strings.isNullOrEmpty(partner.getFirstName()))  {
			return partner.getName() + " " + partner.getFirstName();
		}
		else if(!Strings.isNullOrEmpty(partner.getName()))  {
			return partner.getName();
		}
		else if(!Strings.isNullOrEmpty(partner.getFirstName()))  {
			return partner.getFirstName();
		}
		else  {
			return ""+partner.getId();
		}
	}
	
	
}
