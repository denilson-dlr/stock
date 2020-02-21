# stock
Use case  for stock using hexagonal architecture




SELECT P.NAME
    , P.SKU
    , P.QUANTITY
    , S.BRANCH_CODE
FROM ITEM P
INNER JOIN STOCK_ITEM SI
    on P.SKU = SI.ITEM_ID
INNER JOIN STOCK S
    on SI.STOCK_ID = S.ID WHERE P.SKU =1



SELECT P.NAME
    , P.SKU
    , P.QUANTITY
    , S.TYPE
    , S.BRANCH_CODE
    , S.WAREHOUSE_CODE
FROM ITEM P
INNER JOIN STOCK_ITEM SI
    on P.SKU = SI.ITEM_IDSTOCK 
INNER JOIN STOCK S
    on SI.STOCK_ID = S.ID WHERE S.TYPE = 'BRANCH' AND S.BRANCH_CODE = 1222
    
    
    
    
AXON SERVER: https://download.axoniq.io/axonserver/AxonServer.zip