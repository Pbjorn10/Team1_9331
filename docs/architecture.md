# Architecture

## Overview
Client-server e-commerce app using Java sockets and XML for all storage and messaging. Client is MVC GUI; server handles XML CRUD, search, payment, concurrency-safe stock updates, and logs.

## Modules
- Client: MVC GUI, builds XML requests, parses XML responses.
- Server: Socket listener, per-client threads, request routing, services, XML repositories.
- Shared: Domain models and XML/protocol constants.

## Data Flow
1. Client sends XML request via socket.
2. Server parses XML, routes to controller/service.
3. Server updates XML storage if needed.
4. Server sends XML response.

## Concurrency
Critical sections for stock update and XML writes are synchronized so only one purchase can claim the last unit.

## Manual Start/Stop
Server provides a manual command to start/stop the socket listener without closing the process.
