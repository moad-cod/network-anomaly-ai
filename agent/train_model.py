# train_model.py
import joblib
from sklearn.ensemble import IsolationForest
import numpy as np

# Dummy training data: 100 samples, 4 features
X = np.random.rand(100, 4)

# Train Isolation Forest
model = IsolationForest()
model.fit(X)

# Save the trained model to 'anomaly_model.pkl'
joblib.dump(model, "anomaly_model.pkl")
print("✅ Model saved as anomaly_model.pkl")
