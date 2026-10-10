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

### Session 6: Inheritance, Polymorphism & Gym Management (Collections & Trees)
- **Work Done**:
  - **Class Problems**: Implemented `BatchEnrollmentValidator`, `CirculationReport`, `FineLedger`, `MembershipTree`, and `NightlyCirculationAudit`.
  - **Assignment Problems**: Implemented `GymTrialSignUpValidator`, `ThreeTiersGymMembership`, `LateFeeLedger`, `MonthlyAttendanceAnnouncer`, and `WeeklyCheckInSettlement`.
- **Key Concepts**: Single/multilevel/hierarchical inheritance, runtime polymorphism, constructor validation & chaining (`super`), guarded downcasting with `instanceof`, defensive copying for immutability, overloaded methods, static tracking counters.
### Session 7: Interfaces, Abstract Classes, Multilevel Inheritance & Polymorphism
- **Work Done**:
  - **Class Problems**: Implemented `TalkingToyBox`, `WarehouseLabelPrinter`, `OrchestraWarmUpRoutine`, `SmartKitchenAssistant`, and `PackageDropOffLog`.
  - **Assignment Problems**: Implemented `MorningWakeUpCircuit`, `GalleryDescriptionCards`, `BackyardToolshedRoutine`, `DigitalClassroomSetup`, and `SkylineDeliveryFleet`.
- **Key Concepts**: Interface implementation, abstract classes, static tracking counters via `super()`, multilevel inheritance method chaining, method overloading (compile-time polymorphism), JavaBean validation with range constraints, safe type casting with `instanceof`.
- **Issues & Resolutions**: Ensured constructor validation for non-blank inputs and correctly chained `super` method calls across inheritance tiers.

### Session 8: Object-Oriented Design & Real-World Domain Workflows
- **Work Done**:
  - **Class Problems**: Implemented `VehicleRentalSystem`, `EmployeeLeaveRequestWorkflow`, `OnlineExaminationSystem`, `HotelBookingSystem`, and `PaymentProcessingShoppingSystem`.
  - **Assignment Problems**: Implemented `HostelLaundryQueue`, `AssignmentSubmissionPortal`, `CampusPremiereTicketCounter`, `FitZoneMembershipDesk`, and `CampusNoticeBroadcaster`.
- **Key Concepts**: Domain-driven object modeling, interface/abstract class hierarchies for dynamic pricing and evaluation rules, lifecycle state machines (guarded transitions, terminal states), availability conflict detection, multi-channel notification dispatch.
- **Issues & Resolutions**: Enforced strict state transition checks to prevent invalid reversions from terminal states (e.g., Graded, Expired, Approved/Rejected).

### Session 9: Data Structures (Arrays, Hashing, Two Pointers, Binary Search)
- **Work Done**:
  - **Class Problems**: Implemented `LibraryCatalogLookup`, `WarehouseGridSummary`, `PairWithTargetSum`, and `MaximizeAreaTwoBoundaries`.
  - **Assignment Problems**: Implemented `ClassTopperFinder`, `MergingTwoTokenQueues`, `MostPopularCanteenOrder`, `HotWeatherAlertWindows`, and `TicketPriceSlotFinder`.
- **Key Concepts**: Binary search on sorted structures ($O(\log n)$), 2D matrix sum and coordinate tracking ($O(m \times n)$), single-pass HashSet complement lookup ($O(n)$), two-pointer greedy container area maximization ($O(n)$), linear merge of sorted queues ($O(m+n)$), fixed-size sliding window with running sum ($O(n)$).
- **Issues & Resolutions**: Replaced repeated window sum recalculations with sliding window delta updates to achieve optimal linear time complexity.

### Session 10: OOP Hierarchies, Contracts & Singly Linked Lists
- **Work Done**:
  - **Class Problems**: Implemented `BankAccountWithdrawalSystem`, `CampusVehiclePassSystem`, `StudentClubRegistrationManagement`, `ClassMarksGridAnalysis`, and `PayrollRegisterSystem`.
  - **Assignment Problems**: Implemented `PriorityTokenInsertion`, `CancelledOrdersCleanup`, and `ReverseTheQueue`.
- **Key Concepts**: Polymorphic account constraints (overdraft vs minimum balance), capability interfaces (`Chargeable`), object identity vs equivalence (`equals`/`hashCode`), multi-attribute matrix summaries, custom singly linked list operations (`addFirst`, `addLast`, `insertAt`, `removeAll`, and in-place `reverse` with 3 pointers).
- **Issues & Resolutions**: Built node pointer modifications directly without library collections, handling boundary cases for head removal and in-place pointer reversal.

---

## 🚀 Next Steps
- Merge feature branches into `develop` as sessions complete.
- Continue session assignments and test coverage.

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
| `feature/session_6` | Session 6 Work | `session_six_topics/` (Class & Assignment) |
| `feature/session_7` | Session 7 Work | `session_seven_topics/` (Class & Assignment) |
| `feature/session_8` | Session 8 Work | `session_eight_topics/` (Class & Assignment) |
| `feature/session_9` | Session 9 Work | `session_nine_topics/` (Class & Assignment) |
| `feature/session_10` | Session 10 Work | `session_ten_topics/` (Class & Assignment) |



