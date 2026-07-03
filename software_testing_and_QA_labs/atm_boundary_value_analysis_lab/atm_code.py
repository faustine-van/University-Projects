def atm_withdrawal(amount):
    # Requirement: Allow withdrawals from $20 to $500 inclusive.
    # TODO FOR STUDENTS: Detect the boundary flaw in the conditions below
    if amount < 20:
        return "REJECT: Minimum withdrawal is $20."
    elif amount <= 500:  # <--- HINT FOR INSTRUCTOR: Off-by-one bug here solved
        return "SUCCESS: Please take your cash."
    else:
        return "REJECT: Maximum daily limit is $500."

# --- Interactive Test Bench ---
print("--- ATM Testing Tool ---")
while True:
    user_input = input("\nEnter amount to test (or type 'exit'): ")
    if user_input.lower() == 'exit':
        break
    try:
        test_amount = int(user_input)
        result = atm_withdrawal(test_amount)
        print(f"Result for ${test_amount}: {result}")
    except ValueError:
        print("Invalid input! Please enter an integer number.")