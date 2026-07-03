from momo_engine import MobileMoneyWallet
# Test cases for the MobileMoneyWallet class


def test_success_transfer():
    wallet = MobileMoneyWallet(initial_balance=10000)
    result = wallet.transfer_money(2000)
    assert result is True
    assert wallet.balance == 8000
    
def test_minimum_transfer():
    wallet = MobileMoneyWallet(initial_balance=5000)
    result = wallet.transfer_money(200)
    assert result == "Error: Minimum transfer is 500 RWF"
    assert wallet.balance == 5000

def test_negative_transfer():
    wallet = MobileMoneyWallet(initial_balance=5000)
    result = wallet.transfer_money(-5000)
    assert result == "Error: Invalid amount"

def test_daily_limit_loophole():
    wallet = MobileMoneyWallet(initial_balance=100000)
    wallet.transfer_money(30000)
    result = wallet.transfer_money(30000)
    assert result == "Error: Exceeds daily limit of 50,000 RWF"


def test_insufficient_fund():
    wallet = MobileMoneyWallet(initial_balance=0)
    result = wallet.transfer_money(3000)
    assert result  == "Error: Insufficient funds"