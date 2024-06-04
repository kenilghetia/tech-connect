from xmlrpc.server import SimpleXMLRPCServer

users = {}

def create_user(user_id, name, balance):
    if user_id in users:
        return f"User {user_id} already exists."
    users[user_id] = {"name": name, "balance": balance}
    return f"User {user_id} created."

def get_user(user_id):
    return users.get(user_id, f"User {user_id} not found.")

def main():
    server = SimpleXMLRPCServer(("localhost", 8001))
    print("User Service is listening on port 8001...")
    server.register_function(create_user, "create_user")
    server.register_function(get_user, "get_user")
    server.serve_forever()

if __name__ == "__main__":
    main()
