package com.tvd12.eth.client.example;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.5.0.
 */
public class AmazingDex extends Contract {
    private static final String BINARY = "608060405260018054600160a060020a031916732b7afd366f23f4a426d0c9a584a97976e93f3cbb179055606460025534801561003b57600080fd5b506040516020806102b6833981016040525180600160a060020a038116151561006357600080fd5b5060008054600160a060020a03909216600160a060020a0319909216919091179055610222806100946000396000f3006080604052600436106100565763ffffffff7c01000000000000000000000000000000000000000000000000000000006000350416632c4e722e811461005b57806334fcf43714610082578063a48217191461009a575b600080fd5b34801561006757600080fd5b506100706100a4565b60408051918252519081900360200190f35b34801561008e57600080fd5b506100706004356100aa565b6100a26100da565b005b60025481565b60015460009073ffffffffffffffffffffffffffffffffffffffff1633146100d157600080fd5b50600281905590565b6000338015156100e957600080fd5b600254670de0b6b3a764000090340260008054604080517fa9059cbb0000000000000000000000000000000000000000000000000000000081523360048201529490930460248501819052925192955073ffffffffffffffffffffffffffffffffffffffff169263a9059cbb9260448083019360209383900390910190829087803b15801561017757600080fd5b505af115801561018b573d6000803e3d6000fd5b505050506040513d60208110156101a157600080fd5b505115156101ab57fe5b60015460405173ffffffffffffffffffffffffffffffffffffffff909116903480156108fc02916000818181858888f193505050501580156101f1573d6000803e3d6000fd5b5050505600a165627a7a723058201339f402515dc0901eb2d64c0da4b3e8d5a657e9b85c329a19439a17e7cdeb240029";

    public static final String FUNC_RATE = "rate";

    public static final String FUNC_SETRATE = "setRate";

    public static final String FUNC_BUYTOKEN = "buyToken";

    protected AmazingDex(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected AmazingDex(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<BigInteger> rate() {
        final Function function = new Function(FUNC_RATE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> setRate(BigInteger _rate) {
        final Function function = new Function(
                FUNC_SETRATE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_rate)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> buyToken(BigInteger weiValue) {
        final Function function = new Function(
                FUNC_BUYTOKEN, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public static RemoteCall<AmazingDex> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _amazingTokenAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_amazingTokenAddress)));
        return deployRemoteCall(AmazingDex.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static RemoteCall<AmazingDex> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _amazingTokenAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_amazingTokenAddress)));
        return deployRemoteCall(AmazingDex.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static AmazingDex load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new AmazingDex(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static AmazingDex load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new AmazingDex(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
}
