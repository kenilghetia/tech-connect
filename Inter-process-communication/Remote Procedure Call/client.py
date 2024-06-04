import xmlrpc.client

user_service = xmlrpc.client.ServerProxy("http://localhost:8001/")
transaction_service = xmlrpc.client.ServerProxy("http://localhost:8002/")

# Create a user
print(user_service.create_user(1, "Alice", 1000))

# Perform a transaction
print(transaction_service.perform_transaction(1, -200))

# Check user balance
print(user_service.get_user(1))
