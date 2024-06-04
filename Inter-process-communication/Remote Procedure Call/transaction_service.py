from xmlrpc.server import SimpleXMLRPCServer
import xmlrpc.client

user_service = xmlrpc.client.ServerProxy("http://localhost:8001/")

def perform_transaction(user_id, amount):
    user = user_service.get_user(user_id)
    if isinstance(user, str):  # Check if user is not found
        return user

    new_balance = user['balance'] + amount
    user_service.create_user(user_id, user['name'], new_balance)
    return f"Transaction successful. New balance for user {user_id} is {new_balance}."

def main():
    server = SimpleXMLRPCServer(("localhost", 8002))
    print("Transaction Service is listening on port 8002...")
    server.register_function(perform_transaction, "perform_transaction")
    server.serve_forever()

if __name__ == "__main__":
    main()
