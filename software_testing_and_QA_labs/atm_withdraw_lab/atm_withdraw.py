# def atm_withdraw(balance, amount):
#     # Rule 1: Multplied by 5000 only
#     if amount % 5000 != 0:
#         return "Invalid amount"
    
#     # Rule 2: Max 100, 000 daily limit
#     if amount > 100000:
#         return "Exceeded daily limit"

#     balance -= amount
#     return "Take your cash" 

# Solved way
def atm_withdraw(balance, amount, withdrawn_today):
    if amount <= 0:
        return "Invalid amount"
    
    if amount % 5000 != 0:
        return "Invalid amount"

    if withdrawn_today + amount > 100000:
        return "Exceeded daily limit"

    if amount > balance:
        return "Insufficient balance"

    balance -= amount
    return "Take your cash"
