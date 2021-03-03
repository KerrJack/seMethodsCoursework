**Goal in Context**

_As a user of the system I want to produce a report of all countries in a region organised by largest population to smallest_

**Scope**

_Company_

**Level**

_Primary task_

**Preconditions**

_We know the region. Database contains population data._

**Success End Condition**

_A report is available for the company._

**Failed End Condition**

_No report is produced._

**Primary Actor**

_User._

**Trigger**

_A request for countries in a regions population data is made ordered from largest to smallest._

**MAIN SUCCESS SCENARIO**
_Request for countries population data is made._
_Name of region is captured._
_Program calculates or retrieves population._
_Orders by largest to smallest_
_Report is returned to the user._

**EXTENSIONS**
_region does not exist:
User informs company that region does not exist._
**SUB-VARIATIONS**
_None._