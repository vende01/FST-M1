import pandas as pd;
dataframe=pd.read_csv("sample.csv");
print(dataframe)
print(dataframe["Usernames"])
print(dataframe["Passwords"])
print(dataframe["Usernames"][1],"|",dataframe["Passwords"][1])
print(dataframe.sort_values("Usernames"))
print(dataframe.sort_values("Passwords",ascending=False));