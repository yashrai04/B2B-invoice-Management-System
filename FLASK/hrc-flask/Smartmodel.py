import pickle 
import numpy as np
import pandas as pd

def predict(df):
    
#     df = pd.DataFrame(df)
    
#     df["ORDER_AMOUNT"] = df["ORDER_AMOUNT"].replace("-", "")
#     df["ORDER_AMOUNT"] = df["ORDER_AMOUNT"].replace(",", ".")
#     df['REQUESTED_DELIVERY_DATE'] = df['REQUESTED_DELIVERY_DATE'].replace('-', '')
#     df["ORDER_CREATION_DATE"] = pd.to_datetime(df["ORDER_CREATION_DATE"], format="%Y%m%d")
#     df["REQUESTED_DELIVERY_DATE"] = pd.to_datetime(df["REQUESTED_DELIVERY_DATE"], format="%Y%m%d")
    
#     exchange_rates = {
#     'USD': 1.00,
#     'EUR': 1.07,
#     'AUD': 0.65,
#     'CAD': 0.73,
#     'GBP': 1.23,
#     'MYR': 0.22,
#     'PLN': 0.24,
#     'AED': 0.27,
#     'HKD': 0.13,
#     'CHF': 1.10,
#     'RON': 0.22,
#     'SGD': 0.74,
#     'CZK': 0.045,
#     'HU1': 0.0029,
#     'NZD': 0.61,
#     'BHD': 2.65,
#     'SAR': 0.27,
#     'QAR': 0.27,
#     'KWD': 3.25,
#     'SEK': 0.093
#     }
#     # Defininig a function to convert non-USD currencies to USD
    
#     def convert_to_usd(row):
#         amount = row['ORDER_AMOUNT']
#         currency = row['ORDER_CURRENCY']
        
#         if currency != 'USD':
#             exchange_rate = exchange_rates.get(currency)
            
#             if exchange_rate and pd.notnull(amount):
#                 try:
#                     amount = float(amount)
#                     amount = amount * exchange_rate
#                 except ValueError:
#                     amount = None

#         return amount
#     df['amount_in_usd'] = df.apply(convert_to_usd, axis=1)
    
    
#     df["unique_cust_id"] = df["CUSTOMER_NUMBER"].astype(str) + "_" + df["COMPANY_CODE"].astype(str)
    
    
#     first_quarter = np.percentile(df['amount_in_usd'], 25)
#     third_quarter = np.percentile(df['amount_in_usd'], 75)
#     IQR = third_quarter - first_quarter
    
#     # Defining the outlier boundaries
#     lower_bound = first_quarter - 1.5 * IQR
#     upper_bound = third_quarter + 1.5 * IQR
    
#     # Replacing outliers with the upper and lower bounds
#     df["amount_in_usd"] = df["amount_in_usd"].clip(lower_bound, upper_bound)
    
#     from sklearn.preprocessing import LabelEncoder

#     # Handle mixed data types in the DataFrame

#     # Convert columns with mixed data types to numeric
#     numeric_columns = df.select_dtypes(include=['float', 'int']).columns
#     df[numeric_columns] = df[numeric_columns].apply(pd.to_numeric, errors='coerce')

#     # Convert columns with mixed data types to strings
#     string_columns = df.select_dtypes(include='object').columns
#     df[string_columns] = df[string_columns].astype(str)

#     # Encode the categorical columns using LabelEncoder

#     # Create a label encoder object
#     le = LabelEncoder()

#     # Encode all the categorical columns
#     for col in df.select_dtypes(include='object').columns:
#         df[col] = le.fit_transform(df[col])

    
    
    import xgboost as xgb
    from sklearn.metrics import mean_squared_error, r2_score
    from sklearn.impute import SimpleImputer
    
    # Load the trained XGBoost model
    xg_boost = pickle.load(open('finalized_model.sav', 'rb'))

    # Perform the necessary data preprocessing on the input dataframe
    input_features = ['CUSTOMER_ORDER_ID', 'SALES_ORG', 'DISTRIBUTION_CHANNEL', 'DIVISION',
                 'RELEASED_CREDIT_VALUE', 'PURCHASE_ORDER_TYPE', 'COMPANY_CODE',
                 'ORDER_CREATION_TIME', 'CREDIT_CONTROL_AREA','SOLD_TO_PARTY','ORDER_CURRENCY',
                 'CREDIT_STATUS', 'CUSTOMER_NUMBER','unique_cust_id', 'total_order_amount', 
                 'total_credit_value_by_area', 'order_count','total_order_amount_by_currency', 
                 'ORDER_AMOUNT_max','max_order_amount_by_company_code', 'requested_delivery_date_diff']
    
#      # Remove missing columns from input_features
#     missing_columns = set(input_features) - set(df.columns)
#     input_features_filtered = [col for col in input_features if col not in missing_columns]

#     # Prepare input data for prediction using filtered input_features
#     X_pred = df[input_features_filtered]
    
    X = df[input_features]


#     #imputer = SimpleImputer(strategy='mean')
#     imputer.fit(X)
#     X_imputed = imputer.transform(X)

    # Make predictions using the XGBoost model
    predictions_log = xg_boost.predict(X)  # Predictions in log scale
    
    predictions = np.exp(predictions_log)

    return predictions.tolist()