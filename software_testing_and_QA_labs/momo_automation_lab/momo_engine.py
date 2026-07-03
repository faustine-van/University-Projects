class MobileMoneyWallet:
    def __init__(self, initial_balance):
        self.balance = initial_balance
        self.total_spent_today = 0
        
    def transfer_money(self, amount):
        """
        Processes an outbound ledger transfer request based on system constraints.
        Returns True if authorized, or a string descriptive error if rejected.
        """
        if amount <= 0:
            return "Error: Invalid amount"
        # Rule 1: Validate Minimum Value Constraint
        if amount < 500:
            return "Error: Minimum transfer is 500 RWF"
        # Rule 2: Validate Daily Outbound Speed Cap
        if amount > 50000 or self.total_spent_today + amount > 50000:
            return "Error: Exceeds daily limit of 50,000 RWF"
        # Rule 3: Check Ledger Balance Sufficiency
        if self.balance < amount: 
            return "Error: Insufficient funds"
        
        # Apply state updates upon confirmation
        self.balance -= amount
        self.total_spent_today += amount

        return True