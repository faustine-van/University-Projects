def buy_airtime(balance, amount):

    if amount <= 0:
        return "Invalid amount"

    # Rule 1: Max purchase: is 2000
    if amount > 2000:
        return "Amount is too high"
    
    # Rule 2: Exactly 1000 gives bonus 100
    bonus = 0
    if amount == 1000:
        bonus = 100


    if balance >= amount:
        balance -= amount
        return f"Done! balance is {balance + bonus}"
    

