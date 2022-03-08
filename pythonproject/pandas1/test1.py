import pandas as pd

import numpy as np

s = pd.Series(np.random.randn(5), index=["a", "b", "c", "d", "e"])
d = pd.Series(np.random.randn(1), index=['a'])
d = {
    "one": pd.Series([1.0, 2.0, 3.0], index=["a", "b", "c"]),
    "two": pd.Series([1.0, 2.0, 3.0, 4.0], index=["a", "b", "c", "d"]),
}

df = pd.DataFrame(d)
