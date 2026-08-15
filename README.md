Airbnb Clone — Hotel Booking & Management System

A Spring Boot based hotel booking and management application inspired by the core hotel-booking workflow of Airbnb.

This project is being built as a hands-on Java/Spring Boot backend project, with the implementation developed incrementally across multiple modules covering database design, REST APIs, JPA/Hibernate, booking, dynamic pricing, security, payments, testing, administration, and React integration.

Table of Contents

1. Project Overview

2. Problem Statement

3. Actors

4. Functional Requirements

5. Core User Flows

6. Domain Model

7. Entity Relationships

8. Database Entities

9. Inventory Management

10. Pricing

11. Booking Lifecycle

12. Hotel Search

13. API Design

14. Application Architecture

15. Layer Responsibilities

16. Important Design Considerations

17. Technology Stack

18. Project Modules

19. Git Milestones

20. Future Enhancements

21. Current Project Status

1. Project Overview

The Airbnb Clone is a hotel booking and management system.

The application has two primary sides:

Guest / Customer side

Search for hotels

View hotel and room information

Check room availability

Create bookings

Add guests to bookings

Make payments

View bookings

Hotel Manager side

Create hotels

Update hotel information

Manage room types

Manage inventory

Manage hotel-related information

Admin functionality will be introduced in a later stage of the project.

The initial application is designed as a monolithic Spring Boot application backed by PostgreSQL.

2. Problem Statement

The system provides a platform where users can discover hotels, check availability for specific dates, select room types, create bookings, provide guest information, and complete payments.

At the same time, hotel managers need functionality to manage their hotels, room types, and date-specific inventory.

The system therefore needs to handle:

User accounts

Hotel management

Room type management

Date-specific inventory

Hotel search

Availability checking

Booking management

Guest management

Payment processing

Dynamic pricing

Authentication and authorization

Administrative operations

3. Actors

3.1 Guest

A guest/user can:

Sign up

Log in

Search for hotels

View hotel details

View room details

Check room availability

Create bookings

Add guests to a booking

Make payments

View bookings

Cancel bookings where applicable

3.2 Hotel Manager

A hotel manager can:

Create hotels

Update hotel information

Manage room types

Add room types

Update room information

Delete room types

Manage inventory

Update availability for specific dates

The course design uses the HOTEL_MANAGER role for hotel-management operations.

3.3 Admin

Admin functionality will be introduced during the final backend module.

The exact admin operations will be defined when the Admin API module is implemented.

4. Functional Requirements

4.1 User Management

The system should support:

User registration

User login

User verification

User roles

The primary roles introduced in the design are:

GUEST

HOTEL_MANAGER

Admin functionality will be added later.

4.2 Hotel Management

Hotel managers should be able to:

Create hotels

Update hotel information

Manage hotel status

Manage hotel room types

Manage inventory

A hotel contains information such as:

City

Contact information

Photos

Amenities

Active status

Creation timestamp

Update timestamp

4.3 Room Management

A hotel can contain multiple room types.

For example:

Hotel
├── Single Room
├── Deluxe Room
├── Suite
└── Presidential Suite

The Room entity represents a room type/category rather than an individual physical room.

Room information includes:

Room type

Base price

Amenities

Photos

Total count

Capacity

Creation timestamp

Update timestamp

4.4 Inventory Management

Inventory represents the availability of a room type for a particular date.

Inventory contains information such as:

Hotel

Room

Date

Booked count

Total count

Surge factor

Closed status

Creation timestamp

Update timestamp

Hotel managers can update inventory.

The system can also initialize/manage inventory as part of the application workflow.

4.5 Hotel Search

Guests can search for hotels using parameters such as:

City

Check-in date

Check-out date

Number of rooms

Search results are paginated.

4.6 Booking

A guest can:

Search for a hotel

Select a hotel

Select a room type

Check availability

Create a booking

Add guests

Complete payment

Receive booking confirmation

View bookings

4.7 Guest Management

A user can maintain guest information.

A booking can contain multiple guests.

The relationship between a booking and guests is represented using the BookingGuest entity.

4.8 Payment

Payment is associated with a booking.

The payment domain contains information such as:

Transaction ID

Price

Payment status

Creation timestamp

Update timestamp

Stripe payment integration will be introduced in a later module.

5. Core User Flows

5.1 Guest Booking Flow

User
↓
Login
↓
Search Hotels
↓
Select Hotel
↓
Select Room Type
↓
Check Availability
↓
Create Booking
↓
Add Booking Guests
↓
Payment
↓
Payment Result
↓
Booking Confirmation
↓
View Booking

5.2 Hotel Manager Flow

Hotel Manager
↓
Create Hotel
↓
Add Room Types
↓
Configure Room Information
↓
Manage Inventory
↓
Update Hotel / Room / Inventory

6. Domain Model

The initial domain model contains nine key entities:

1. User
2. Guest
3. Hotel
4. ContactInfo
5. Room
6. Inventory
7. Booking
8. BookingGuest
9. Payment

7. Entity Relationships

The current Week 1 design establishes the following relationships:

User 1 ───── * Guest

User 1 ───── * Booking

Hotel 1 ───── 1 ContactInfo

Hotel 1 ───── * Room

Room 1 ───── * Inventory

Booking 1 ───── 1 Payment

Booking * ───── * Guest
│
BookingGuest

Relationship Explanation

User → Guest

One user can maintain multiple guest records.

User
├── Guest 1
├── Guest 2
└── Guest 3

User → Booking

One user can create multiple bookings.

Each booking belongs to one user.

User
├── Booking 1
├── Booking 2
└── Booking 3

Hotel → ContactInfo

A hotel has contact information containing details such as:

Complete address

Location

Email

Phone number

Hotel → Room

One hotel can contain multiple room types.

Hotel
├── Single
├── Deluxe
└── Suite

Each room type belongs to one hotel.

Room → Inventory

One room type can have multiple inventory records for different dates.

Deluxe Room
├── Inventory - Aug 20
├── Inventory - Aug 21
├── Inventory - Aug 22
└── Inventory - Aug 23

Booking → Payment

The Week 1 design models one payment for one booking.

Booking 1 ───── 1 Payment

The payment implementation will be expanded when Stripe integration is introduced.

Booking ↔ Guest

A booking can contain multiple guests.

A guest can appear in multiple bookings over time.

Therefore, the relationship is represented as:

Booking * ───── * Guest
│
BookingGuest

BookingGuest acts as the junction entity.

8. Database Entities

8.1 User

The User entity represents an application account.

Attributes:

id
roles
name
email
password

8.2 Guest

The Guest entity represents guest information associated with a user.

Attributes:

id
userId
name
createdAt
gender

8.3 Hotel

The Hotel entity represents a hotel managed through the application.

Attributes:

id
city
contactInfo
photos
createdAt
updatedAt
amenities
active

8.4 ContactInfo

ContactInfo contains hotel contact and location information.

Attributes:

id
completeAddress
location
email
phoneNumber

8.5 Room

Room represents a room type/category within a hotel.

Attributes:

id
hotelId
type
basePrice
createdAt
updatedAt
amenities
photos
totalCount
capacity

8.6 Inventory

Inventory represents date-specific room availability and pricing information.

Attributes:

id
hotelId
roomId
date
bookedCount
totalCount
createdAt
updatedAt
surgeFactor
closed

8.7 Booking

Booking represents a reservation created by a user.

Attributes:

id
hotelId
roomId
userId
createdAt
updatedAt
status
checkInDate
checkoutDate
paymentId

8.8 BookingGuest

BookingGuest connects bookings with guests.

Attributes:

id
bookingId
guestId

8.9 Payment

Payment represents the payment associated with a booking.

Attributes:

id
transactionId
price
createdAt
updatedAt
status

9. Inventory Management

Inventory is date-specific.

A room type can have different availability for different dates.

Example:

Deluxe Room
Total Count = 10

Date        Booked    Available
--------------------------------
Aug 20         7          3
Aug 21         9          1
Aug 22         4          6
Aug 23         2          8

Availability is therefore not a single property of the room type.

It depends on:

Room Type
+
Date
+
Inventory

Inventory Closed Status

The closed field indicates whether the inventory for a room type/date is available for booking.

closed = false
→ Inventory can be considered for booking

closed = true
→ Inventory is not available for booking

Inventory Updates

Inventory can be managed for individual room types and dates.

For example:

Deluxe Room
Aug 20

totalCount = 10
bookedCount = 7

If one additional room becomes available:

totalCount = 11
bookedCount = 7

The existing date-specific inventory record should be updated rather than creating duplicate inventory records for the same room type/date.

10. Pricing

A room type has a base price.

Inventory contains a date-specific surgeFactor.

Conceptually:

Room
↓
Base Price
↓
Inventory for requested date
↓
Surge Factor
↓
Effective Price

Example:

Base Price = ₹5,000

Date        Surge Factor
-------------------------
Aug 20         1.0
Aug 21         1.5
Aug 22         1.2
Aug 23         0.8

The base price should not need to be continuously modified for date-specific demand.

Dynamic pricing functionality will be implemented in a later module.

11. Booking Lifecycle

The initial booking lifecycle is:

PENDING
│
├── Payment Success ──→ CONFIRMED
│
└── Payment Failure ──→ FAILED

CONFIRMED
│
└── Cancellation ──→ CANCELLED

Candidate booking states:

PENDING
CONFIRMED
FAILED
CANCELLED

The exact implementation and status representation will be finalized during the booking implementation.

12. Hotel Search

The search functionality accepts parameters such as:

city
checkInDate
checkOutDate
numberOfRooms

Example:

Search:

City          = Bangalore
Check-in      = 2026-08-20
Check-out     = 2026-08-22
Rooms         = 2

The system searches for hotels matching the supplied criteria and availability.

Search results are paginated.

Why Pagination?

Returning a very large number of hotels in one response can result in:

Larger response payloads

Increased network transfer

More data processing

Higher memory usage

Slower response handling

Pagination allows the client to request a limited set of results.

Conceptually:

1,000 matching hotels

        ↓

Page 0 → 20 hotels
Page 1 → 20 hotels
Page 2 → 20 hotels
...

13. API Design

The Week 1 design identifies the following API areas.

13.1 Authentication APIs

POST /api/v1/auth/signup
POST /api/v1/auth/login
POST /api/v1/auth/verify

Authentication and authorization will be implemented in the security modules.

13.2 Hotel Manager APIs

Hotel Manager APIs are responsible for hotel, room, and inventory management.

Representative operations include:

POST   /api/v1/admin/hotels
GET    /api/v1/admin/hotels
GET    /api/v1/admin/hotels/{hotelId}
PATCH  /api/v1/admin/hotels/{hotelId}

POST   /api/v1/admin/hotels/{hotelId}/rooms
GET    /api/v1/admin/hotels/{hotelId}/rooms
GET    /api/v1/admin/hotels/{hotelId}/rooms/{roomId}
PATCH  /api/v1/admin/hotels/{hotelId}/rooms/{roomId}
DELETE /api/v1/admin/hotels/{hotelId}/rooms/{roomId}

Inventory management:

PATCH /api/v1/admin/inventory/{hotelId}/{roomId}/{date}

The exact API contracts will be verified and finalized during implementation.

13.3 Guest / Search APIs

GET /api/v1/hotels/search
GET /api/v1/hotels/{hotelId}
GET /api/v1/hotels/{hotelId}/rooms/{roomId}

13.4 Booking APIs

Representative booking operations include:

POST  /api/v1/bookings
GET   /api/v1/bookings
GET   /api/v1/bookings/{bookingId}
PATCH /api/v1/bookings/cancel

Guest management:

POST /api/v1/guests

Payment:

POST /api/v1/payments/{bookingId}

The exact request and response contracts will be established during implementation.

14. Application Architecture

The project is initially implemented as a monolithic Spring Boot application.

High-level architecture:

                    Client
                      │
                      ▼
                REST Controller
                      │
                      ▼
                    Service
                      │
          ┌───────────┴───────────┐
          │                       │
          ▼                       ▼
      Repository             Other Services
          │
          ▼
      PostgreSQL

15. Layer Responsibilities

Controller Layer

Responsible for HTTP/API concerns:

Receive HTTP requests

Map request data

Validate request input

Invoke the service layer

Return appropriate HTTP responses

Controllers should not contain the core business logic.

Service Layer

Responsible for business logic.

Examples:

Booking rules

Availability checks

Booking state transitions

Pricing logic

Inventory updates

Coordination between multiple repositories/services

Example:

BookingController
↓
BookingService
↓
┌─────┴───────────────┐
↓                     ↓
BookingRepository   InventoryRepository

Repository Layer

Responsible for persistence and database access.

Examples:

save()
findById()
findBy...
delete()

Repositories should not contain the main business rules of the application.

16. Important Design Considerations

16.1 Backend Validation

Validation must be performed on the backend even when the UI performs validation.

Example:

numberOfRooms = -2

The backend should reject invalid input with an appropriate error response.

Similarly:

checkInDate >= checkOutDate

should be rejected.

The backend remains responsible for enforcing valid API input.

16.2 Concurrency

Booking introduces a concurrency problem.

Example:

Available rooms = 1

User A ──→ Check availability ──→ 1 available
User B ──→ Check availability ──→ 1 available

Both attempt to book

The system must prevent overbooking when multiple requests attempt to book the same available inventory concurrently.

The detailed concurrency solution will be addressed during booking implementation.

16.3 Pagination

Hotel search uses pagination so that large result sets are not returned in a single response.

16.4 Hotel Manager Ownership

The Week 1 business discussion identifies that a hotel manager should manage hotels associated with them.

However, the supplied Week 1 ERD does not explicitly show a managerId or userId field on Hotel.

This is therefore an open design question.

It will be reviewed against the actual entity/schema implementation in Module 2 before making a final decision.

16.5 Hotel Active Status

The Hotel entity contains an active field.

Conceptually:

active = true
→ Hotel is active

active = false
→ Hotel is not active for normal booking

The exact administrative behavior around hotel deactivation will be defined during the Admin API implementation.

17. Technology Stack

Core

Java 25

Spring Boot

Maven

PostgreSQL

Spring

Spring Web

Spring Data JPA

Lombok

Planned Later

Spring Security

JWT

Stripe

Springdoc / OpenAPI

Testing

Docker / containerization

CI/CD

React

Additional technologies will be introduced as the corresponding project modules are implemented.

18. Project Modules

The project follows the course structure while using the material as a learning reference rather than blindly copying the implementation.

Module

Topic

Implementation

1

Requirements Gathering, Schema Design, ERD, API Design

Design only

2

Setup Schema and Entity Relationship

Yes

3

Hotel Manager APIs

Yes

4

Search and Booking

Yes

5

Dynamic Hotel Pricing and Scheduling

Yes

6

Authentication and Authorization

Yes

7

Testing + Stripe Payment/Refund

Yes

8

Admin APIs / Finalization

Yes

19. Git Milestones

The project will use major milestone commits rather than committing every small implementation change.

Commit 1 — Requirements / Design

Contains:

Project foundation

Week 1 requirements

Domain understanding

Entity relationships

API design

Architecture documentation

Commit 2 — Schema / Entities

Contains:

JPA entities

Entity relationships

Database schema

PostgreSQL integration

Commit 3 — Hotel Manager APIs

Contains:

Hotel APIs

Room APIs

Inventory management APIs

Commit 4 — Search / Booking

Contains:

Hotel search

Availability

Booking creation

Booking management

Commit 5 — Dynamic Pricing / Scheduling

Contains:

Dynamic pricing

Pricing strategies

Scheduling functionality

Commit 6 — Authentication / Authorization

Contains:

Spring Security

Authentication

JWT

Roles

Authorization

Commit 7 — Testing / Stripe / Final Backend

Contains:

Testing

Stripe payment integration

Refund functionality

Final backend/admin functionality

Commit 8 — React UI

Contains:

React frontend

Backend integration

UI changes

Final frontend updates

20. Future Enhancements

The initial project is intentionally implemented as a monolithic Spring Boot application.

Potential future architectural improvements can be evaluated after the core system is complete, including:

Microservices

Caching

Messaging

Improved scalability

Additional search capabilities

More advanced payment handling

Improved observability

Production deployment improvements

These are not part of the initial Week 1 implementation unless introduced by a later project module.

21. Current Project Status

Week 1 — Requirements & Design

[✓] Requirements identified
[✓] Actors identified
[✓] Core user flows identified
[✓] Domain entities identified
[✓] Entity relationships discussed
[✓] Inventory model discussed
[✓] Pricing model discussed
[✓] Booking lifecycle discussed
[✓] Search requirements discussed
[✓] API areas identified
[✓] Layered architecture identified
[✓] Validation requirements identified
[✓] Concurrency concern identified
[✓] Open design questions documented

Implementation Status

Module 1  → Design complete
Module 2  → Not started
Module 3  → Not started
Module 4  → Not started
Module 5  → Not started
Module 6  → Not started
Module 7  → Not started
Module 8  → Not started
React UI  → Planned

Learning Objective

The goal of this project is not only to build a working application.

The project is being developed to understand and demonstrate:

Java

Spring Boot

Spring MVC

REST API design

Spring Data JPA

Hibernate

PostgreSQL

Database relationships

Transactions

Concurrency

Validation

Authentication

Authorization

JWT

Dynamic pricing

Scheduling

Payment gateway integration

Testing

API documentation

Containerization

CI/CD

React integration

Each major feature will be implemented with an emphasis on understanding why the design and implementation are used, rather than blindly reproducing tutorial code.