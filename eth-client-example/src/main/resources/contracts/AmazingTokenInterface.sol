pragma solidity ^0.4.11;

contract AmazingTokenInterface{
    uint256 public totalSupply;
    function () public payable;
    function balanceOf(address _owner)
    constant public returns(uint256);
    function transfer(address _to, uint256 _value)
    public returns(bool);
}

contract AmazingDex {
    AmazingTokenInterface AmazingToken;
    address ChiroWallet = 0x2b7afd366f23f4a426d0c9a584a97976e93f3cbb;
    uint256 public rate = 100;
    modifier onlyValidAddress(address _to){
        require(_to != address(0x00));
        _;
    }
    
    modifier onlyChiro(){
        require(msg.sender == ChiroWallet);
        _;
    }
    
    function setRate(uint256 _rate)
    onlyChiro public returns(uint256){
        rate = _rate;
        return rate;
    }
    
    function AmazingDex(address _amazingTokenAddress) 
    onlyValidAddress(_amazingTokenAddress) public {
        AmazingToken = AmazingTokenInterface(_amazingTokenAddress);
    }
    
    function buyToken()
    onlyValidAddress(msg.sender) public payable {
        uint256 _value = (msg.value*rate)/10**18;
        assert(AmazingToken.transfer(msg.sender, _value));
        ChiroWallet.transfer(msg.value);
    }
}