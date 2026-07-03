from airtime_promo import buy_airtime
import pytest

@pytest.mark.parametrize(
        "balance, amount, expected",
        [(1000, -1, "Invalid amount"), 
         (1000, 0, "Invalid amount")])


def test_invalid_amount(balance, amount, expected):
    res = buy_airtime(balance, amount)
    assert res == expected

@pytest.mark.parametrize(
        "bal, amt, exp",
        [(100000, 2000, "Done! balance is 98000"), 
         (100000, 1999, "Done! balance is 98001"),]
                         )

def test_normal_amount(bal, amt, exp):
    result = buy_airtime(bal, amt)
    assert result == exp

def test_high_amount():
    res = buy_airtime(1000, 2001)
    assert res == "Amount is too high"

def test_exactly_1000():
    res = buy_airtime(1000, 1000)
    assert res == "Done! balance is 100"

def test_insufficient_balance():
    res = buy_airtime(500, 600)
    assert res == None
