# Step_semester_3

Java Practice and Assignment Repository for Semester 3.

---

## 📅 Daily Work Log

### Session 1: Basics & Control Flow
- **Work Done**:
  - **Class Problems**: Implemented `BmiCalculator`, `FirstNonRepeatingChar`, `PalindromeChecker`, `ReverseCustomerName`, and `RockPaperScissors`.
  - **Assignment Problems**: Implemented `MovieReviewWordLengthProfiler`, `SeatDuplicationChecker`, `TrafficSignalStreakAnalyzer`, `TypingSpeedAccuracyChecker`, and `WarehouseInventoryBalancer`.
- **Key Concepts**: Basic syntax, loops, conditional statements, arrays, and standard I/O.
- **Issues & Resolutions**: Ensured boundary checks for streaks and frequency counting.

### Session 2: Strings & Parsing
- **Work Done**:
  - **Class Problems**: Implemented `BankTransactionValidator`, `FileExtensionValidator`, `MaskedPhoneNumber`, `StudentRecordParser`, and `VowelConsonantCounter`.
  - **Assignment Problems**: Implemented `AtmPinLengthValidator`, `LibraryIsbnValidator`, `ProductInventoryCsvParser`, `StopWordFilteredFrequencyReport`, and `WordReversalEncoder`.
- **Key Concepts**: String manipulation, StringBuilder, regex validation, CSV string parsing, word tokenization.
- **Issues & Resolutions**: Corrected edge cases for empty and malformed string inputs.

### Session 3: Object-Oriented Fundamentals & Constructors
- **Work Done**:
  - **Class Problems**: Implemented `Course`, `IdCard`, `MessWallet`, `PlacementRecord`, and `Student`.
  - **Assignment Problems**: Implemented `BookInventory`, `Employee`, `EmployeeStatic`, `HallTicket`, and `PayrollAccount`.
- **Key Concepts**: Class vs. instance variables, static methods, constructor overloading, encapsulation.
- **Issues & Resolutions**: Handled static counter tracking accurately across instances.

### Session 4: Inheritance & Polymorphism
- **Work Done**:
  - **Class Problems**: Implemented `AccountBatchPayments`, `Employee`, `LateFeeAccount`, `LibraryBook`, and `SrmStudent`.
  - **Assignment Problems**: Implemented `Item`, `MembershipCard`, `ParkingTicket`, `Participant`, and `PaymentDispatch`.
- **Key Concepts**: Method overriding, `super` keyword, abstract classes/interfaces, polymorphic method dispatch.
- **Issues & Resolutions**: Handled subclass initialization ordering and superclass constructor chaining.

### Session 5: Access Modifiers, Encapsulation & JavaBeans
- **Work Done**:
  - **Class Problems**: Implemented `MovieTicketAccessChecker`, `SubclassTicketAccessChecker`, `CineScreen`, `MovieBookingProfile`, and `SettlementProcessor`.
  - **Assignment Problems**: Implemented `MembershipReachChecker`, `ReferenceDeskReachChecker`, `BookInventory`, `LibraryMember`, and `CirculationLedger`.
- **Key Concepts**: Access modifiers (`private`, `protected`, `public`, package-private), JavaBean getter/setter conventions, `Serializable`, write-only properties (OTP, security answers).
- **Issues & Resolutions**: Resolved compiler warnings for write-only security fields using `@SuppressWarnings("unused")`.

---

## 🚀 Next Steps
- Implement and test **Session 6** topics (Data Structures / Tree & Ledger structures: `FineLedger`, `MembershipTree`, `NightlyCirculationAudit`, `BatchEnrollmentValidator`, `CirculationReport`).
- Merge feature branches into `develop` as sessions complete.

---

## 🌿 Branching Strategy

| Branch | Purpose | Contents |
|---|---|---|
| `main` | Production & Documentation | Dated logs, architecture, documentation |
| `develop` | Base Development Branch | Empty Java project structure |
| `feature/session_1` | Session 1 Work | `session_one_topics/` (Class & Assignment) |
| `feature/session_2` | Session 2 Work | `session_two_topics/` (Class & Assignment) |
| `feature/session_3` | Session 3 Work | `session_three_topics/` (Class & Assignment) |
| `feature/session_4` | Session 4 Work | `session_four_topics/` (Class & Assignment) |
| `feature/session_5` | Session 5 Work | `session_five_topics/` (Class & Assignment) |
