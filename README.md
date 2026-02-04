# Team1_9331

## Overview
Java client-server e-commerce app for local businesses using sockets and XML for all storage and messaging. Client uses MVC GUI; server handles XML CRUD, search, payment, concurrency-safe stock updates, and logs.

## Project Layout
- `src/main/java/com/team1/ecommerce/server` Server-side app, socket listener, request handling, services, XML repositories, logging, concurrency controls.
- `src/main/java/com/team1/ecommerce/client` Client-side MVC GUI, socket client, request/response handling.
- `src/main/java/com/team1/ecommerce/shared` Shared domain models and protocol constants.
- `src/main/resources/data` XML data files used by the server.
- `docs` Architecture and protocol notes.

## Package and Class Skeletons
### Server
- `com.team1.ecommerce.server.ServerApp` Entry point (manual start/stop control).
- `com.team1.ecommerce.server.net.SocketServer` Socket listener lifecycle.
- `com.team1.ecommerce.server.net.ClientHandler` Per-client thread.
- `com.team1.ecommerce.server.controller.RequestRouter` Maps XML actions to controllers.
- `com.team1.ecommerce.server.controller.ProductController` Product CRUD.
- `com.team1.ecommerce.server.controller.CartController` Cart CRUD.
- `com.team1.ecommerce.server.controller.PaymentController` Payment flow + stock update.
- `com.team1.ecommerce.server.controller.SearchController` Product search.
- `com.team1.ecommerce.server.controller.ReportController` Sales report.
- `com.team1.ecommerce.server.service.ProductService` Business logic for products.
- `com.team1.ecommerce.server.service.CartService` Business logic for cart.
- `com.team1.ecommerce.server.service.PaymentService` Purchase + transaction write.
- `com.team1.ecommerce.server.service.ReportService` Aggregates transactions by date.
- `com.team1.ecommerce.server.service.UserService` Auth/user lookup.
- `com.team1.ecommerce.server.repository.xml.XmlProductDAO` XML persistence for products.
- `com.team1.ecommerce.server.repository.xml.XmlUserDAO` XML persistence for users.
- `com.team1.ecommerce.server.repository.xml.XmlOrderDAO` XML persistence for transactions.
- `com.team1.ecommerce.server.repository.xml.XmlCartDAO` Optional XML persistence for carts.
- `com.team1.ecommerce.server.xml.XmlStore` XML load/save coordinator.
- `com.team1.ecommerce.server.xml.XmlLockManager` Concurrency locks for XML + stock.
- `com.team1.ecommerce.server.logging.ServerLogger` Transaction log output.

### Client
- `com.team1.ecommerce.client.ClientApp` Entry point for GUI.
- `com.team1.ecommerce.client.net.SocketClient` Connects and exchanges XML.
- `com.team1.ecommerce.client.net.XmlRequestBuilder` Builds XML requests.
- `com.team1.ecommerce.client.net.XmlResponseParser` Parses XML responses.
- `com.team1.ecommerce.client.controller.LoginController` Login flow.
- `com.team1.ecommerce.client.controller.BusinessDashboardController` Business CRUD UI.
- `com.team1.ecommerce.client.controller.ResidentShopController` Browse/search UI.
- `com.team1.ecommerce.client.controller.CartController` Cart UI actions.
- `com.team1.ecommerce.client.controller.ReportController` Sales report UI.
- `com.team1.ecommerce.client.view.LoginView` Login screen.
- `com.team1.ecommerce.client.view.BusinessDashboardView` Product list + form.
- `com.team1.ecommerce.client.view.ProductFormView` Add/edit product.
- `com.team1.ecommerce.client.view.ProductListView` List with edit/delete.
- `com.team1.ecommerce.client.view.SalesReportView` Date-range report view.
- `com.team1.ecommerce.client.view.ShopView` Product list + search + add to cart.
- `com.team1.ecommerce.client.view.CartView` Cart list + pay.
- `com.team1.ecommerce.client.model.ClientSession` Current user/session state.
- `com.team1.ecommerce.client.model.CartState` Client-side cart view state.

### Shared
- `com.team1.ecommerce.shared.model.Product` Product entity.
- `com.team1.ecommerce.shared.model.User` User entity (business/resident).
- `com.team1.ecommerce.shared.model.CartItem` Cart line item.
- `com.team1.ecommerce.shared.model.Cart` Cart aggregate.
- `com.team1.ecommerce.shared.model.Transaction` Completed purchase.
- `com.team1.ecommerce.shared.protocol.Action` Action names for XML.
- `com.team1.ecommerce.shared.protocol.Status` Response status values.
- `com.team1.ecommerce.shared.protocol.ErrorCode` Error codes.
- `com.team1.ecommerce.shared.xml.XmlTags` Common XML tag names.

## XML Storage (Server)
Location: `src/main/resources/data`
- `products.xml` Product catalog.
- `users.xml` Business and resident users.
- `transactions.xml` Sales history.

### Sample XML Shapes (Schema Draft)
`products.xml`
```xml
<products>
  <product id="P001">
    <name>Milk</name>
    <price>50</price>
    <stock>20</stock>
    <category>Dairy</category>
  </product>
</products>
```

`users.xml`
```xml
<users>
  <user id="U001" role="BUSINESS">
    <username>seller1</username>
    <password>pass</password>
  </user>
  <user id="U100" role="RESIDENT">
    <username>buyer1</username>
    <password>pass</password>
  </user>
</users>
```

`transactions.xml`
```xml
<transactions>
  <transaction id="T001">
    <datetime>2026-02-10T10:32:00</datetime>
    <userId>U100</userId>
    <productId>P001</productId>
    <quantity>2</quantity>
    <totalPrice>100</totalPrice>
  </transaction>
</transactions>
```

## XML Messaging (Client ↔ Server)
- Requests and responses are XML with action names and payload.
- Example request:
```xml
<request>
  <action>ADD_PRODUCT</action>
  <name>Milk</name>
  <price>50</price>
  <stock>20</stock>
</request>
```
- Example response:
```xml
<response>
  <status>SUCCESS</status>
  <message>Product added</message>
</response>
```

## Concurrency Rule
Stock updates and XML writes must be synchronized so only one purchase can claim the last unit. The server uses `XmlLockManager` and synchronized critical sections.

## Server Logging Format
`[YYYY-MM-DD HH:MM] user action details`
Example:
`[2026-02-10 10:32] User Karim purchased Milk x2 (Stock: 10 -> 8)`

## Manual Start/Stop
The server supports manual commands (start/stop) in `ServerApp` to control the socket listener lifecycle.
