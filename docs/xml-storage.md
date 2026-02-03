# XML Storage

## Location
`src/main/resources/data`

## Files
- products.xml
- users.xml
- transactions.xml

## Sample Shapes
```
<products>
  <product id="P001">
    <name>Milk</name>
    <price>50</price>
    <stock>20</stock>
    <category>Dairy</category>
  </product>
</products>
```

```
<users>
  <user id="U001" role="BUSINESS">
    <username>seller1</username>
    <password>pass</password>
  </user>
</users>
```

```
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
