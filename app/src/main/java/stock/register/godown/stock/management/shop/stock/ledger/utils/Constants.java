package stock.register.godown.stock.management.shop.stock.ledger.utils;

public class Constants {
    // base url
    public static final String BASE_URL = "http://fafd22683885.ngrok.io/";
    public static final String BUSINESS_ID_VALUE = "11111";


    //create party api in party fragment
    public static final String CREATE_PARTY = "api/v1/party/createParty";
    public static final String GET_PARTY = "api/v1/party/getParty/";

    // inputs field in create party api
    public static final String NAME = "name";
    public static final String PARTY_MOBILE_NUMBER = "mobile";
    public static final String BUSINESS_ID = "businessId";


    //create store api in store fragment
    public static final String CREATE_STORE = "api/v1/store/createStore";
    public static final String GET_STORE = "api/v1/store/getStore/";


    //create stock item api item fragment
    public static final String CREATE_STOCK_ITEM = "api/v1/stockItem/createStockItem";
    //input fields
    public static final String PURCHASE_PRICE = "purchasePrice";
    public static final String SELLING_PRICE = "sellingPrice";
    public static final String MRP = "mrp";
    public static final String PRICE_UNIT = "priceUnit";
    public static final String STOCK_STORE_DETAILS = "stockStoreDetails";




    // Other constants
    public static final int CONTACTS_RESULT_CODE = 424;
    public static final int CONTACTS_REQUEST_CODE = 76;
    public static final String ADD_STORE_BOOLEAN = "Add store boolean";
    public static final String ADD_PARTY_BOOLEAN = "Add party boolean";



}
