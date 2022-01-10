import pandas as pd

df = pd.read_csv('totalSales.csv')
df.to_html('./totalSales.html')
