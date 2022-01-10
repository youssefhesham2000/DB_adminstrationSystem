import pandas as pd
df = pd.read_csv('topTen.csv')
df.to_html('./topTen.html')
