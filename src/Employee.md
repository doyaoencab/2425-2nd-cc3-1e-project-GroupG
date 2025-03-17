```mermaid
---
title: Employee
---
classDiagram
  Employee <|-- Payroll
  Employee <|-- Leave
  Employee <|-- Recruitment
  Recruitment <|-- Interview
  Employee <-- Calendar
  Payroll <-- Calendar
  Recruitment <-- Calendar
  Interview <-- Calendar

  //classes
  class Employee {
    - employeeID: int
    - name: String
    - age: int
    - department: String
    - position: String
    - salary: double
    - hiredDate: int
    - leaveBalance: int
    + viewDetails()
    + updateDetails()
    + calculateSalary()
    + promote()
  }

  class Payroll {
    - basicSalary: double
    - bonuses: double
    - deductions: double
    - netSalary: double
    - payrollMonth: double
    + generatePayslip()
    + calculateDeductions()
    + addBonuses()
  }

  class Leave {
    - leaveType: String
    - startDate: int
    - endDate: int
    - status: String
    + applyForLeave()
    + approveLeave()
    + rejectLeave()
    + calculateLeaveBalance()
  }

  class Recruitment {
    - jobTitle: String
    - jobDescription: String
    - requiredSkills: String
    - applications: String
    - status: String
    + postJobOpening()
    + reviewApplications()
    + scheduleInterview()
    + hireCandidate()
  }

  class Interview {
    - interviewID: int
    - candidateID: int
    - interviewerID: int
    - scheduleDate: int
    - feedback: String
    + recordFeedback()
  }

  class Calendar {
    - date: int
    + addEvent()
    + removeEvent()
    + viewMonthlySchedule()
  }
