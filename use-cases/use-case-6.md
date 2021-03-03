**Goal in Context**

_As a user of the system I want to produce a report of the top (N) populated countries in the world where I will give the value to (N)_

**Scope**

_Company_

**Level**

_Primary task_

**Preconditions**

_We know the value of (N). We know the name of the country. Database contains population data._

**Success End Condition**

_A report is available for the company._

**Failed End Condition**

_No report is produced._

**Primary Actor**

_User._

**Trigger**

_A request for the top (N) populated countries in the world given the value of (N)_

**MAIN SUCCESS SCENARIO**
_Request for countries population data is made._
_Name of countries is captured._
_Program calculates or retrieves population._
_Orders from largest population to smallest given the value of (N)_
_Report is returned to the user._

**EXTENSIONS**
_number not applicable
Invalid input is given for the value of (N)_
**SUB-VARIATIONS**
_None._