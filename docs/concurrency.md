# Concurrency Control

## Requirement
Only one resident can purchase the last available unit.

## Rule
Stock read/update and XML write are synchronized to prevent races.

## Failure Response
Second buyer receives an OUT_OF_STOCK error response.
