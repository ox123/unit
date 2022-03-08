from sklearn import datasets
import numpy as np
from sklearn.linear_model import LogisticRegression
from sklearn.model_selection import train_test_split
from sklearn.neural_network import MLPClassifier
from sklearn import svm
from sklearn import tree

# 导入样本数据
wine = datasets.load_wine()
x = wine.data
y = wine.target
print(np.shape(x), np.shape(y))
# print(wine)

x_tran, x_test, y_train, y_test = train_test_split(x, y, test_size=0.2, random_state=42)
print(np.shape(x_tran), np.shape(x_test))

model = LogisticRegression().fit(x_tran, y_train)
print(model.score(x_tran, y_train))
print(model.score(x_test, y_test))

# 支持向量机模型  SVC
print("--SVM---")
model = svm.SVC().fit(x_tran, y_train)
print(model.score(x_tran, y_train))
print(model.score(x_test, y_test))

# 决策树
print("--tree---")
model = tree.DecisionTreeClassifier().fit(x_tran, y_train)
print(model.score(x_tran, y_train))
print(model.score(x_test, y_test))

# 神经网络
print("--neutral network---")
model = MLPClassifier(alpha=1e-5,
                      hidden_layer_sizes=200,
                      solver="lbs",
                      random_state=1).fit(x_tran, y_train)
print(model.score(x_tran, y_train))
print(model.score(x_test, y_test))
