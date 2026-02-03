# XML Protocol

## Request Envelope
```
<request>
  <action>ACTION_NAME</action>
  ...payload...
</request>
```

## Response Envelope
```
<response>
  <status>SUCCESS|ERROR</status>
  <message>Human readable message</message>
  ...payload...
</response>
```

## Example Actions
- ADD_PRODUCT
- READ_PRODUCTS
- UPDATE_PRODUCT
- DELETE_PRODUCT
- SEARCH_PRODUCT
- CART_ADD
- CART_UPDATE
- CART_REMOVE
- CART_VIEW
- PAY
- REPORT_RANGE

## Error Codes
- OUT_OF_STOCK
- INVALID_REQUEST
- NOT_FOUND
