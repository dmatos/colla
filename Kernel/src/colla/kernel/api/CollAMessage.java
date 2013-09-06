package colla.kernel.api;

/**
 * Messages are the way to communicate with servers, hosts and users. A message
 * should carry an identifier of the operation to be carried out at the destination.
 * Objects carried by a message must implement Serializable.
 * 
 * @author Diogo Matos <dmatos88 at gmail.com>
 */
public interface CollAMessage {
    
    /**
     * @return an Enum representing the operation to be performed by the receiver
     */
     @SuppressWarnings("rawtypes")
	public Enum getOperation();
}
