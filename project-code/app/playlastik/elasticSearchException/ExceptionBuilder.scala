package playlastik.elasticSearchException

import playlastik.models.ESFailure

/**
 * Created by fred on 19/11/14.
 */
object ExceptionBuilder {

  val ActionRequestValidationExceptionP	 = "^ActionRequestValidationException\\[(.*)\\]".r
  val BenchmarkExecutionExceptionP	 = "^BenchmarkExecutionException\\[(.*)\\]".r
  val BenchmarkMissingExceptionP	 = "^BenchmarkMissingException\\[(.*)\\]".r
  val BenchmarkNodeMissingExceptionP	 = "^BenchmarkNodeMissingException\\[(.*)\\]".r
  val FailedNodeExceptionP	 = "^FailedNodeException\\[(.*)\\]".r
  val NoShardAvailableActionExceptionP	 = "^NoShardAvailableActionException\\[(.*)\\]".r
  val NoSuchNodeExceptionP	 = "^NoSuchNodeException\\[(.*)\\]".r
  val PrimaryMissingActionExceptionP	 = "^PrimaryMissingActionException\\[(.*)\\]".r
  val RoutingMissingExceptionP	 = "^RoutingMissingException\\[(.*)\\]".r
  val ReduceSearchPhaseExceptionP	 = "^ReduceSearchPhaseException\\[(.*)\\]".r
  val SearchPhaseExecutionExceptionP	 = "^SearchPhaseExecutionException\\[(.*)\\]".r
  val ShardOperationFailedExceptionP	 = "^ShardOperationFailedException\\[(.*)\\]".r
  val BroadcastShardOperationFailedExceptionP	 = "^BroadcastShardOperationFailedException\\[(.*)\\]".r
  val DefaultShardOperationFailedExceptionP	 = "^DefaultShardOperationFailedException\\[(.*)\\]".r
  val ReplicationShardOperationFailedExceptionP	 = "^ReplicationShardOperationFailedException\\[(.*)\\]".r
  val TimestampParsingExceptionP	 = "^TimestampParsingException\\[(.*)\\]".r
  val UnavailableShardsExceptionP	 = "^UnavailableShardsException\\[(.*)\\]".r
  val NoNodeAvailableExceptionP	 = "^NoNodeAvailableException\\[(.*)\\]".r
  val ClusterBlockExceptionP	 = "^ClusterBlockException\\[(.*)\\]".r
  val ProcessClusterEventTimeoutExceptionP	 = "^ProcessClusterEventTimeoutException\\[(.*)\\]".r
  val IllegalShardRoutingStateExceptionP	 = "^IllegalShardRoutingStateException\\[(.*)\\]".r
  val RoutingExceptionP	 = "^RoutingException\\[(.*)\\]".r
  val RoutingValidationExceptionP	 = "^RoutingValidationException\\[(.*)\\]".r
  val BlobStoreExceptionP	 = "^BlobStoreException\\[(.*)\\]".r
  val CircuitBreakingExceptionP	 = "^CircuitBreakingException\\[(.*)\\]".r
  val ConfigurationExceptionP	 = "^ConfigurationException\\[(.*)\\]".r
  val CreationExceptionP	 = "^CreationException\\[(.*)\\]".r
  val AsynchronousComputationExceptionP	 = "^AsynchronousComputationException\\[(.*)\\]".r
  val ComputationExceptionP	 = "^ComputationException\\[(.*)\\]".r
  val ErrorsExceptionP	 = "^ErrorsException\\[(.*)\\]".r
  val NullOutputExceptionP	 = "^NullOutputException\\[(.*)\\]".r
  val OutOfScopeExceptionP	 = "^OutOfScopeException\\[(.*)\\]".r
  val ProvisionExceptionP	 = "^ProvisionException\\[(.*)\\]".r
  val NoClassSettingsExceptionP	 = "^NoClassSettingsException\\[(.*)\\]".r
  val SettingsExceptionP	 = "^SettingsException\\[(.*)\\]".r
  val NetworkExceptionHelperP	 = "^NetworkExceptionHelper\\[(.*)\\]".r
  val EsRejectedExecutionExceptionP	 = "^EsRejectedExecutionException\\[(.*)\\]".r
  val UncategorizedExecutionExceptionP	 = "^UncategorizedExecutionException\\[(.*)\\]".r
  val DiscoveryExceptionP	 = "^DiscoveryException\\[(.*)\\]".r
  val MasterNotDiscoveredExceptionP	 = "^MasterNotDiscoveredException\\[(.*)\\]".r
  val ZenPingExceptionP	 = "^ZenPingException\\[(.*)\\]".r
  val ElasticsearchExceptionP	 = "^ElasticsearchException\\[(.*)\\]".r
  val ElasticsearchGenerationExceptionP	 = "^ElasticsearchGenerationException\\[(.*)\\]".r
  val ElasticsearchIllegalArgumentExceptionP	 = "^ElasticsearchIllegalArgumentException\\[(.*)\\]".r
  val ElasticsearchIllegalStateExceptionP	 = "^ElasticsearchIllegalStateException\\[(.*)\\]".r
  val ElasticsearchNullPointerExceptionP	 = "^ElasticsearchNullPointerException\\[(.*)\\]".r
  val ElasticsearchParseExceptionP	 = "^ElasticsearchParseException\\[(.*)\\]".r
  val ElasticsearchTimeoutExceptionP	 = "^ElasticsearchTimeoutException\\[(.*)\\]".r
  val ElasticsearchWrapperExceptionP	 = "^ElasticsearchWrapperException\\[(.*)\\]".r
  val FailedToResolveConfigExceptionP	 = "^FailedToResolveConfigException\\[(.*)\\]".r
  val ExceptionsHelperP	 = "^ExceptionsHelper\\[(.*)\\]".r
  val GatewayExceptionP	 = "^GatewayException\\[(.*)\\]".r
  val BindHttpExceptionP	 = "^BindHttpException\\[(.*)\\]".r
  val HttpExceptionP	 = "^HttpException\\[(.*)\\]".r
  val AlreadyExpiredExceptionP	 = "^AlreadyExpiredException\\[(.*)\\]".r
  val CloseEngineExceptionP	 = "^CloseEngineException\\[(.*)\\]".r
  val CreateFailedEngineExceptionP	 = "^CreateFailedEngineException\\[(.*)\\]".r
  val DeleteByQueryFailedEngineExceptionP	 = "^DeleteByQueryFailedEngineException\\[(.*)\\]".r
  val DeleteFailedEngineExceptionP	 = "^DeleteFailedEngineException\\[(.*)\\]".r
  val DocumentAlreadyExistsExceptionP	 = "^DocumentAlreadyExistsException\\[(.*)\\]".r
  val DocumentMissingExceptionP	 = "^DocumentMissingException\\[(.*)\\]".r
  val DocumentSourceMissingExceptionP	 = "^DocumentSourceMissingException\\[(.*)\\]".r
  val EngineAlreadyStartedExceptionP	 = "^EngineAlreadyStartedException\\[(.*)\\]".r
  val EngineClosedExceptionP	 = "^EngineClosedException\\[(.*)\\]".r
  val EngineCreationFailureExceptionP	 = "^EngineCreationFailureException\\[(.*)\\]".r
  val EngineExceptionP	 = "^EngineException\\[(.*)\\]".r
  val FlushFailedEngineExceptionP	 = "^FlushFailedEngineException\\[(.*)\\]".r
  val FlushNotAllowedEngineExceptionP	 = "^FlushNotAllowedEngineException\\[(.*)\\]".r
  val IgnoreOnRecoveryEngineExceptionP	 = "^IgnoreOnRecoveryEngineException\\[(.*)\\]".r
  val IndexFailedEngineExceptionP	 = "^IndexFailedEngineException\\[(.*)\\]".r
  val OptimizeFailedEngineExceptionP	 = "^OptimizeFailedEngineException\\[(.*)\\]".r
  val RecoveryEngineExceptionP	 = "^RecoveryEngineException\\[(.*)\\]".r
  val RefreshFailedEngineExceptionP	 = "^RefreshFailedEngineException\\[(.*)\\]".r
  val RollbackFailedEngineExceptionP	 = "^RollbackFailedEngineException\\[(.*)\\]".r
  val RollbackNotAllowedEngineExceptionP	 = "^RollbackNotAllowedEngineException\\[(.*)\\]".r
  val SnapshotFailedEngineExceptionP	 = "^SnapshotFailedEngineException\\[(.*)\\]".r
  val VersionConflictEngineExceptionP	 = "^VersionConflictEngineException\\[(.*)\\]".r
  val IgnoreGatewayRecoveryExceptionP	 = "^IgnoreGatewayRecoveryException\\[(.*)\\]".r
  val IndexShardGatewayExceptionP	 = "^IndexShardGatewayException\\[(.*)\\]".r
  val IndexShardGatewayRecoveryExceptionP	 = "^IndexShardGatewayRecoveryException\\[(.*)\\]".r
  val IndexShardGatewaySnapshotFailedExceptionP	 = "^IndexShardGatewaySnapshotFailedException\\[(.*)\\]".r
  val IndexShardGatewaySnapshotNotAllowedExceptionP	 = "^IndexShardGatewaySnapshotNotAllowedException\\[(.*)\\]".r
  val IndexExceptionP	 = "^IndexException\\[(.*)\\]".r
  val IndexShardAlreadyExistsExceptionP	 = "^IndexShardAlreadyExistsException\\[(.*)\\]".r
  val IndexShardMissingExceptionP	 = "^IndexShardMissingException\\[(.*)\\]".r
  val MapperCompressionExceptionP	 = "^MapperCompressionException\\[(.*)\\]".r
  val MapperExceptionP	 = "^MapperException\\[(.*)\\]".r
  val MapperParsingExceptionP	 = "^MapperParsingException\\[(.*)\\]".r
  val MergeMappingExceptionP	 = "^MergeMappingException\\[(.*)\\]".r
  val StrictDynamicMappingExceptionP	 = "^StrictDynamicMappingException\\[(.*)\\]".r
  val PercolatorExceptionP	 = "^PercolatorException\\[(.*)\\]".r
  val QueryParsingExceptionP	 = "^QueryParsingException\\[(.*)\\]".r
  val IllegalIndexShardStateExceptionP	 = "^IllegalIndexShardStateException\\[(.*)\\]".r
  val IndexShardClosedExceptionP	 = "^IndexShardClosedException\\[(.*)\\]".r
  val IndexShardCreationExceptionP	 = "^IndexShardCreationException\\[(.*)\\]".r
  val IndexShardExceptionP	 = "^IndexShardException\\[(.*)\\]".r
  val IndexShardNotRecoveringExceptionP	 = "^IndexShardNotRecoveringException\\[(.*)\\]".r
  val IndexShardNotStartedExceptionP	 = "^IndexShardNotStartedException\\[(.*)\\]".r
  val IndexShardRecoveringExceptionP	 = "^IndexShardRecoveringException\\[(.*)\\]".r
  val IndexShardRelocatedExceptionP	 = "^IndexShardRelocatedException\\[(.*)\\]".r
  val IndexShardStartedExceptionP	 = "^IndexShardStartedException\\[(.*)\\]".r
  val IndexShardRestoreExceptionP	 = "^IndexShardRestoreException\\[(.*)\\]".r
  val IndexShardRestoreFailedExceptionP	 = "^IndexShardRestoreFailedException\\[(.*)\\]".r
  val IndexShardSnapshotExceptionP	 = "^IndexShardSnapshotException\\[(.*)\\]".r
  val IndexShardSnapshotFailedExceptionP	 = "^IndexShardSnapshotFailedException\\[(.*)\\]".r
  val StoreExceptionP	 = "^StoreException\\[(.*)\\]".r
  val TranslogExceptionP	 = "^TranslogException\\[(.*)\\]".r
  val AliasFilterParsingExceptionP	 = "^AliasFilterParsingException\\[(.*)\\]".r
  val IndexAlreadyExistsExceptionP	 = "^IndexAlreadyExistsException\\[(.*)\\]".r
  val IndexClosedExceptionP	 = "^IndexClosedException\\[(.*)\\]".r
  val IndexCreationExceptionP	 = "^IndexCreationException\\[(.*)\\]".r
  val IndexMissingExceptionP	 = "^IndexMissingException\\[(.*)\\]".r
  val IndexPrimaryShardNotAllocatedExceptionP	 = "^IndexPrimaryShardNotAllocatedException\\[(.*)\\]".r
  val IndexTemplateAlreadyExistsExceptionP	 = "^IndexTemplateAlreadyExistsException\\[(.*)\\]".r
  val IndexTemplateMissingExceptionP	 = "^IndexTemplateMissingException\\[(.*)\\]".r
  val InvalidAliasNameExceptionP	 = "^InvalidAliasNameException\\[(.*)\\]".r
  val InvalidIndexNameExceptionP	 = "^InvalidIndexNameException\\[(.*)\\]".r
  val InvalidIndexTemplateExceptionP	 = "^InvalidIndexTemplateException\\[(.*)\\]".r
  val InvalidTypeNameExceptionP	 = "^InvalidTypeNameException\\[(.*)\\]".r
  val DelayRecoveryExceptionP	 = "^DelayRecoveryException\\[(.*)\\]".r
  val IgnoreRecoveryExceptionP	 = "^IgnoreRecoveryException\\[(.*)\\]".r
  val RecoverFilesRecoveryExceptionP	 = "^RecoverFilesRecoveryException\\[(.*)\\]".r
  val RecoveryFailedExceptionP	 = "^RecoveryFailedException\\[(.*)\\]".r
  val TypeMissingExceptionP	 = "^TypeMissingException\\[(.*)\\]".r
  val NodeClosedExceptionP	 = "^NodeClosedException\\[(.*)\\]".r
  val PercolateExceptionP	 = "^PercolateException\\[(.*)\\]".r
  val RepositoryExceptionP	 = "^RepositoryException\\[(.*)\\]".r
  val RepositoryMissingExceptionP	 = "^RepositoryMissingException\\[(.*)\\]".r
  val AliasesMissingExceptionP	 = "^AliasesMissingException\\[(.*)\\]".r
  val RiverExceptionP	 = "^RiverException\\[(.*)\\]".r
  val ExpressionScriptCompilationExceptionP	 = "^ExpressionScriptCompilationException\\[(.*)\\]".r
  val ExpressionScriptExecutionExceptionP	 = "^ExpressionScriptExecutionException\\[(.*)\\]".r
  val GroovyScriptCompilationExceptionP	 = "^GroovyScriptCompilationException\\[(.*)\\]".r
  val GroovyScriptExecutionExceptionP	 = "^GroovyScriptExecutionException\\[(.*)\\]".r
  val ScriptExceptionP	 = "^ScriptException\\[(.*)\\]".r
  val AggregationExecutionExceptionP	 = "^AggregationExecutionException\\[(.*)\\]".r
  val AggregationInitializationExceptionP	 = "^AggregationInitializationException\\[(.*)\\]".r
  val SearchSourceBuilderExceptionP	 = "^SearchSourceBuilderException\\[(.*)\\]".r
  val DfsPhaseExecutionExceptionP	 = "^DfsPhaseExecutionException\\[(.*)\\]".r
  val FacetPhaseExecutionExceptionP	 = "^FacetPhaseExecutionException\\[(.*)\\]".r
  val FetchPhaseExecutionExceptionP	 = "^FetchPhaseExecutionException\\[(.*)\\]".r
  val QueryPhaseExecutionExceptionP	 = "^QueryPhaseExecutionException\\[(.*)\\]".r
  val SearchContextExceptionP	 = "^SearchContextException\\[(.*)\\]".r
  val SearchContextMissingExceptionP	 = "^SearchContextMissingException\\[(.*)\\]".r
  val SearchExceptionP	 = "^SearchException\\[(.*)\\]".r
  val SearchParseExceptionP	 = "^SearchParseException\\[(.*)\\]".r
  val IndexWarmerMissingExceptionP	 = "^IndexWarmerMissingException\\[(.*)\\]".r
  val ConcurrentSnapshotExecutionExceptionP	 = "^ConcurrentSnapshotExecutionException\\[(.*)\\]".r
  val InvalidSnapshotNameExceptionP	 = "^InvalidSnapshotNameException\\[(.*)\\]".r
  val SnapshotCreationExceptionP	 = "^SnapshotCreationException\\[(.*)\\]".r
  val SnapshotExceptionP	 = "^SnapshotException\\[(.*)\\]".r
  val SnapshotMissingExceptionP	 = "^SnapshotMissingException\\[(.*)\\]".r
  val SnapshotRestoreExceptionP	 = "^SnapshotRestoreException\\[(.*)\\]".r
  val ActionNotFoundTransportExceptionP	 = "^ActionNotFoundTransportException\\[(.*)\\]".r
  val ActionTransportExceptionP	 = "^ActionTransportException\\[(.*)\\]".r
  val BindTransportExceptionP	 = "^BindTransportException\\[(.*)\\]".r
  val ConnectTransportExceptionP	 = "^ConnectTransportException\\[(.*)\\]".r
  val FailedCommunicationExceptionP	 = "^FailedCommunicationException\\[(.*)\\]".r
  val NodeDisconnectedExceptionP	 = "^NodeDisconnectedException\\[(.*)\\]".r
  val NodeNotConnectedExceptionP	 = "^NodeNotConnectedException\\[(.*)\\]".r
  val NodeShouldNotConnectExceptionP	 = "^NodeShouldNotConnectException\\[(.*)\\]".r
  val NotSerializableTransportExceptionP	 = "^NotSerializableTransportException\\[(.*)\\]".r
  val ReceiveTimeoutTransportExceptionP	 = "^ReceiveTimeoutTransportException\\[(.*)\\]".r
  val RemoteTransportExceptionP	 = "^RemoteTransportException\\[(.*)\\]".r
  val ResponseHandlerFailureTransportExceptionP	 = "^ResponseHandlerFailureTransportException\\[(.*)\\]".r
  val ResponseHandlerNotFoundTransportExceptionP	 = "^ResponseHandlerNotFoundTransportException\\[(.*)\\]".r
  val SendRequestTransportExceptionP	 = "^SendRequestTransportException\\[(.*)\\]".r
  val TransportExceptionP	 = "^TransportException\\[(.*)\\]".r
  val TransportSerializationExceptionP	 = "^TransportSerializationException\\[(.*)\\]".r
  val elasticElasticsearchExceptionTestsP	 = "^elasticElasticsearchExceptionTests\\[(.*)\\]".r
  val RandomExceptionCircuitBreakerTestsP	 = "^RandomExceptionCircuitBreakerTests\\[(.*)\\]".r
  val SearchWithRandomExceptionsTestsP	 = "^SearchWithRandomExceptionsTests\\[(.*)\\]".r
  val RestExceptionP	 = "^RestException\\[(.*)\\]".r
  val RestTestParseExceptionP = "^RestTestParseException\\[(.*)\\]".r

  def getException(failure : ESFailure) = {
    failure.error match {
      case ActionRequestValidationExceptionP(msg) => 	throw new 	 ActionRequestValidationException(msg)
      case BenchmarkExecutionExceptionP(msg) => 	throw new 	 BenchmarkExecutionException(msg)
      case BenchmarkMissingExceptionP(msg) => 	throw new 	 BenchmarkMissingException(msg)
      case BenchmarkNodeMissingExceptionP(msg) => 	throw new 	 BenchmarkNodeMissingException(msg)
      case FailedNodeExceptionP(msg) => 	throw new 	 FailedNodeException(msg)
      case NoShardAvailableActionExceptionP(msg) => 	throw new 	 NoShardAvailableActionException(msg)
      case NoSuchNodeExceptionP(msg) => 	throw new 	 NoSuchNodeException(msg)
      case PrimaryMissingActionExceptionP(msg) => 	throw new 	 PrimaryMissingActionException(msg)
      case RoutingMissingExceptionP(msg) => 	throw new 	 RoutingMissingException(msg)
      case ReduceSearchPhaseExceptionP(msg) => 	throw new 	 ReduceSearchPhaseException(msg)
      case SearchPhaseExecutionExceptionP(msg) => 	throw new 	 SearchPhaseExecutionException(msg)
      case ShardOperationFailedExceptionP(msg) => 	throw new 	 ShardOperationFailedException(msg)
      case BroadcastShardOperationFailedExceptionP(msg) => 	throw new 	 BroadcastShardOperationFailedException(msg)
      case DefaultShardOperationFailedExceptionP(msg) => 	throw new 	 DefaultShardOperationFailedException(msg)
      case ReplicationShardOperationFailedExceptionP(msg) => 	throw new 	 ReplicationShardOperationFailedException(msg)
      case TimestampParsingExceptionP(msg) => 	throw new 	 TimestampParsingException(msg)
      case UnavailableShardsExceptionP(msg) => 	throw new 	 UnavailableShardsException(msg)
      case NoNodeAvailableExceptionP(msg) => 	throw new 	 NoNodeAvailableException(msg)
      case ClusterBlockExceptionP(msg) => 	throw new 	 ClusterBlockException(msg)
      case ProcessClusterEventTimeoutExceptionP(msg) => 	throw new 	 ProcessClusterEventTimeoutException(msg)
      case IllegalShardRoutingStateExceptionP(msg) => 	throw new 	 IllegalShardRoutingStateException(msg)
      case RoutingExceptionP(msg) => 	throw new 	 RoutingException(msg)
      case RoutingValidationExceptionP(msg) => 	throw new 	 RoutingValidationException(msg)
      case BlobStoreExceptionP(msg) => 	throw new 	 BlobStoreException(msg)
      case CircuitBreakingExceptionP(msg) => 	throw new 	 CircuitBreakingException(msg)
      case ConfigurationExceptionP(msg) => 	throw new 	 ConfigurationException(msg)
      case CreationExceptionP(msg) => 	throw new 	 CreationException(msg)
      case AsynchronousComputationExceptionP(msg) => 	throw new 	 AsynchronousComputationException(msg)
      case ComputationExceptionP(msg) => 	throw new 	 ComputationException(msg)
      case ErrorsExceptionP(msg) => 	throw new 	 ErrorsException(msg)
      case NullOutputExceptionP(msg) => 	throw new 	 NullOutputException(msg)
      case OutOfScopeExceptionP(msg) => 	throw new 	 OutOfScopeException(msg)
      case ProvisionExceptionP(msg) => 	throw new 	 ProvisionException(msg)
      case NoClassSettingsExceptionP(msg) => 	throw new 	 NoClassSettingsException(msg)
      case SettingsExceptionP(msg) => 	throw new 	 SettingsException(msg)
      case NetworkExceptionHelperP(msg) => 	throw new 	 NetworkExceptionHelper(msg)
      case EsRejectedExecutionExceptionP(msg) => 	throw new 	 EsRejectedExecutionException(msg)
      case UncategorizedExecutionExceptionP(msg) => 	throw new 	 UncategorizedExecutionException(msg)
      case DiscoveryExceptionP(msg) => 	throw new 	 DiscoveryException(msg)
      case MasterNotDiscoveredExceptionP(msg) => 	throw new 	 MasterNotDiscoveredException(msg)
      case ZenPingExceptionP(msg) => 	throw new 	 ZenPingException(msg)
      case ElasticsearchGenerationExceptionP(msg) => 	throw new 	 ElasticsearchGenerationException(msg)
      case ElasticsearchIllegalArgumentExceptionP(msg) => 	throw new 	 ElasticsearchIllegalArgumentException(msg)
      case ElasticsearchIllegalStateExceptionP(msg) => 	throw new 	 ElasticsearchIllegalStateException(msg)
      case ElasticsearchNullPointerExceptionP(msg) => 	throw new 	 ElasticsearchNullPointerException(msg)
      case ElasticsearchParseExceptionP(msg) => 	throw new 	 ElasticsearchParseException(msg)
      case ElasticsearchTimeoutExceptionP(msg) => 	throw new 	 ElasticsearchTimeoutException(msg)
      case ElasticsearchWrapperExceptionP(msg) => 	throw new 	 ElasticsearchWrapperException(msg)
      case FailedToResolveConfigExceptionP(msg) => 	throw new 	 FailedToResolveConfigException(msg)
      case ExceptionsHelperP(msg) => 	throw new 	 ExceptionsHelper(msg)
      case GatewayExceptionP(msg) => 	throw new 	 GatewayException(msg)
      case BindHttpExceptionP(msg) => 	throw new 	 BindHttpException(msg)
      case HttpExceptionP(msg) => 	throw new 	 HttpException(msg)
      case AlreadyExpiredExceptionP(msg) => 	throw new 	 AlreadyExpiredException(msg)
      case CloseEngineExceptionP(msg) => 	throw new 	 CloseEngineException(msg)
      case CreateFailedEngineExceptionP(msg) => 	throw new 	 CreateFailedEngineException(msg)
      case DeleteByQueryFailedEngineExceptionP(msg) => 	throw new 	 DeleteByQueryFailedEngineException(msg)
      case DeleteFailedEngineExceptionP(msg) => 	throw new 	 DeleteFailedEngineException(msg)
      case DocumentAlreadyExistsExceptionP(msg) => 	throw new 	 DocumentAlreadyExistsException(msg)
      case DocumentMissingExceptionP(msg) => 	throw new 	 DocumentMissingException(msg)
      case DocumentSourceMissingExceptionP(msg) => 	throw new 	 DocumentSourceMissingException(msg)
      case EngineAlreadyStartedExceptionP(msg) => 	throw new 	 EngineAlreadyStartedException(msg)
      case EngineClosedExceptionP(msg) => 	throw new 	 EngineClosedException(msg)
      case EngineCreationFailureExceptionP(msg) => 	throw new 	 EngineCreationFailureException(msg)
      case EngineExceptionP(msg) => 	throw new 	 EngineException(msg)
      case FlushFailedEngineExceptionP(msg) => 	throw new 	 FlushFailedEngineException(msg)
      case FlushNotAllowedEngineExceptionP(msg) => 	throw new 	 FlushNotAllowedEngineException(msg)
      case IgnoreOnRecoveryEngineExceptionP(msg) => 	throw new 	 IgnoreOnRecoveryEngineException(msg)
      case IndexFailedEngineExceptionP(msg) => 	throw new 	 IndexFailedEngineException(msg)
      case OptimizeFailedEngineExceptionP(msg) => 	throw new 	 OptimizeFailedEngineException(msg)
      case RecoveryEngineExceptionP(msg) => 	throw new 	 RecoveryEngineException(msg)
      case RefreshFailedEngineExceptionP(msg) => 	throw new 	 RefreshFailedEngineException(msg)
      case RollbackFailedEngineExceptionP(msg) => 	throw new 	 RollbackFailedEngineException(msg)
      case RollbackNotAllowedEngineExceptionP(msg) => 	throw new 	 RollbackNotAllowedEngineException(msg)
      case SnapshotFailedEngineExceptionP(msg) => 	throw new 	 SnapshotFailedEngineException(msg)
      case VersionConflictEngineExceptionP(msg) => 	throw new 	 VersionConflictEngineException(msg)
      case IgnoreGatewayRecoveryExceptionP(msg) => 	throw new 	 IgnoreGatewayRecoveryException(msg)
      case IndexShardGatewayExceptionP(msg) => 	throw new 	 IndexShardGatewayException(msg)
      case IndexShardGatewayRecoveryExceptionP(msg) => 	throw new 	 IndexShardGatewayRecoveryException(msg)
      case IndexShardGatewaySnapshotFailedExceptionP(msg) => 	throw new 	 IndexShardGatewaySnapshotFailedException(msg)
      case IndexShardGatewaySnapshotNotAllowedExceptionP(msg) => 	throw new 	 IndexShardGatewaySnapshotNotAllowedException(msg)
      case IndexExceptionP(msg) => 	throw new 	 IndexException(msg)
      case IndexShardAlreadyExistsExceptionP(msg) => 	throw new 	 IndexShardAlreadyExistsException(msg)
      case IndexShardMissingExceptionP(msg) => 	throw new 	 IndexShardMissingException(msg)
      case MapperCompressionExceptionP(msg) => 	throw new 	 MapperCompressionException(msg)
      case MapperExceptionP(msg) => 	throw new 	 MapperException(msg)
      case MapperParsingExceptionP(msg) => 	throw new 	 MapperParsingException(msg)
      case MergeMappingExceptionP(msg) => 	throw new 	 MergeMappingException(msg)
      case StrictDynamicMappingExceptionP(msg) => 	throw new 	 StrictDynamicMappingException(msg)
      case PercolatorExceptionP(msg) => 	throw new 	 PercolatorException(msg)
      case QueryParsingExceptionP(msg) => 	throw new 	 QueryParsingException(msg)
      case IllegalIndexShardStateExceptionP(msg) => 	throw new 	 IllegalIndexShardStateException(msg)
      case IndexShardClosedExceptionP(msg) => 	throw new 	 IndexShardClosedException(msg)
      case IndexShardCreationExceptionP(msg) => 	throw new 	 IndexShardCreationException(msg)
      case IndexShardExceptionP(msg) => 	throw new 	 IndexShardException(msg)
      case IndexShardNotRecoveringExceptionP(msg) => 	throw new 	 IndexShardNotRecoveringException(msg)
      case IndexShardNotStartedExceptionP(msg) => 	throw new 	 IndexShardNotStartedException(msg)
      case IndexShardRecoveringExceptionP(msg) => 	throw new 	 IndexShardRecoveringException(msg)
      case IndexShardRelocatedExceptionP(msg) => 	throw new 	 IndexShardRelocatedException(msg)
      case IndexShardStartedExceptionP(msg) => 	throw new 	 IndexShardStartedException(msg)
      case IndexShardRestoreExceptionP(msg) => 	throw new 	 IndexShardRestoreException(msg)
      case IndexShardRestoreFailedExceptionP(msg) => 	throw new 	 IndexShardRestoreFailedException(msg)
      case IndexShardSnapshotExceptionP(msg) => 	throw new 	 IndexShardSnapshotException(msg)
      case IndexShardSnapshotFailedExceptionP(msg) => 	throw new 	 IndexShardSnapshotFailedException(msg)
      case StoreExceptionP(msg) => 	throw new 	 StoreException(msg)
      case TranslogExceptionP(msg) => 	throw new 	 TranslogException(msg)
      case AliasFilterParsingExceptionP(msg) => 	throw new 	 AliasFilterParsingException(msg)
      case IndexAlreadyExistsExceptionP(msg) => 	throw new 	 IndexAlreadyExistsException(msg)
      case IndexClosedExceptionP(msg) => 	throw new 	 IndexClosedException(msg)
      case IndexCreationExceptionP(msg) => 	throw new 	 IndexCreationException(msg)
      case IndexMissingExceptionP(msg) => 	throw new 	 IndexMissingException(msg)
      case IndexPrimaryShardNotAllocatedExceptionP(msg) => 	throw new 	 IndexPrimaryShardNotAllocatedException(msg)
      case IndexTemplateAlreadyExistsExceptionP(msg) => 	throw new 	 IndexTemplateAlreadyExistsException(msg)
      case IndexTemplateMissingExceptionP(msg) => 	throw new 	 IndexTemplateMissingException(msg)
      case InvalidAliasNameExceptionP(msg) => 	throw new 	 InvalidAliasNameException(msg)
      case InvalidIndexNameExceptionP(msg) => 	throw new 	 InvalidIndexNameException(msg)
      case InvalidIndexTemplateExceptionP(msg) => 	throw new 	 InvalidIndexTemplateException(msg)
      case InvalidTypeNameExceptionP(msg) => 	throw new 	 InvalidTypeNameException(msg)
      case DelayRecoveryExceptionP(msg) => 	throw new 	 DelayRecoveryException(msg)
      case IgnoreRecoveryExceptionP(msg) => 	throw new 	 IgnoreRecoveryException(msg)
      case RecoverFilesRecoveryExceptionP(msg) => 	throw new 	 RecoverFilesRecoveryException(msg)
      case RecoveryFailedExceptionP(msg) => 	throw new 	 RecoveryFailedException(msg)
      case TypeMissingExceptionP(msg) => 	throw new 	 TypeMissingException(msg)
      case NodeClosedExceptionP(msg) => 	throw new 	 NodeClosedException(msg)
      case PercolateExceptionP(msg) => 	throw new 	 PercolateException(msg)
      case RepositoryExceptionP(msg) => 	throw new 	 RepositoryException(msg)
      case RepositoryMissingExceptionP(msg) => 	throw new 	 RepositoryMissingException(msg)
      case AliasesMissingExceptionP(msg) => 	throw new 	 AliasesMissingException(msg)
      case RiverExceptionP(msg) => 	throw new 	 RiverException(msg)
      case ExpressionScriptCompilationExceptionP(msg) => 	throw new 	 ExpressionScriptCompilationException(msg)
      case ExpressionScriptExecutionExceptionP(msg) => 	throw new 	 ExpressionScriptExecutionException(msg)
      case GroovyScriptCompilationExceptionP(msg) => 	throw new 	 GroovyScriptCompilationException(msg)
      case GroovyScriptExecutionExceptionP(msg) => 	throw new 	 GroovyScriptExecutionException(msg)
      case ScriptExceptionP(msg) => 	throw new 	 ScriptException(msg)
      case AggregationExecutionExceptionP(msg) => 	throw new 	 AggregationExecutionException(msg)
      case AggregationInitializationExceptionP(msg) => 	throw new 	 AggregationInitializationException(msg)
      case SearchSourceBuilderExceptionP(msg) => 	throw new 	 SearchSourceBuilderException(msg)
      case DfsPhaseExecutionExceptionP(msg) => 	throw new 	 DfsPhaseExecutionException(msg)
      case FacetPhaseExecutionExceptionP(msg) => 	throw new 	 FacetPhaseExecutionException(msg)
      case FetchPhaseExecutionExceptionP(msg) => 	throw new 	 FetchPhaseExecutionException(msg)
      case QueryPhaseExecutionExceptionP(msg) => 	throw new 	 QueryPhaseExecutionException(msg)
      case SearchContextExceptionP(msg) => 	throw new 	 SearchContextException(msg)
      case SearchContextMissingExceptionP(msg) => 	throw new 	 SearchContextMissingException(msg)
      case SearchExceptionP(msg) => 	throw new 	 SearchException(msg)
      case SearchParseExceptionP(msg) => 	throw new 	 SearchParseException(msg)
      case IndexWarmerMissingExceptionP(msg) => 	throw new 	 IndexWarmerMissingException(msg)
      case ConcurrentSnapshotExecutionExceptionP(msg) => 	throw new 	 ConcurrentSnapshotExecutionException(msg)
      case InvalidSnapshotNameExceptionP(msg) => 	throw new 	 InvalidSnapshotNameException(msg)
      case SnapshotCreationExceptionP(msg) => 	throw new 	 SnapshotCreationException(msg)
      case SnapshotExceptionP(msg) => 	throw new 	 SnapshotException(msg)
      case SnapshotMissingExceptionP(msg) => 	throw new 	 SnapshotMissingException(msg)
      case SnapshotRestoreExceptionP(msg) => 	throw new 	 SnapshotRestoreException(msg)
      case ActionNotFoundTransportExceptionP(msg) => 	throw new 	 ActionNotFoundTransportException(msg)
      case ActionTransportExceptionP(msg) => 	throw new 	 ActionTransportException(msg)
      case BindTransportExceptionP(msg) => 	throw new 	 BindTransportException(msg)
      case ConnectTransportExceptionP(msg) => 	throw new 	 ConnectTransportException(msg)
      case FailedCommunicationExceptionP(msg) => 	throw new 	 FailedCommunicationException(msg)
      case NodeDisconnectedExceptionP(msg) => 	throw new 	 NodeDisconnectedException(msg)
      case NodeNotConnectedExceptionP(msg) => 	throw new 	 NodeNotConnectedException(msg)
      case NodeShouldNotConnectExceptionP(msg) => 	throw new 	 NodeShouldNotConnectException(msg)
      case NotSerializableTransportExceptionP(msg) => 	throw new 	 NotSerializableTransportException(msg)
      case ReceiveTimeoutTransportExceptionP(msg) => 	throw new 	 ReceiveTimeoutTransportException(msg)
      case RemoteTransportExceptionP(msg) => 	throw new 	 RemoteTransportException(msg)
      case ResponseHandlerFailureTransportExceptionP(msg) => 	throw new 	 ResponseHandlerFailureTransportException(msg)
      case ResponseHandlerNotFoundTransportExceptionP(msg) => 	throw new 	 ResponseHandlerNotFoundTransportException(msg)
      case SendRequestTransportExceptionP(msg) => 	throw new 	 SendRequestTransportException(msg)
      case TransportExceptionP(msg) => 	throw new 	 TransportException(msg)
      case TransportSerializationExceptionP(msg) => 	throw new 	 TransportSerializationException(msg)
      case elasticElasticsearchExceptionTestsP(msg) => 	throw new 	 elasticElasticsearchExceptionTests(msg)
      case RandomExceptionCircuitBreakerTestsP(msg) => 	throw new 	 RandomExceptionCircuitBreakerTests(msg)
      case SearchWithRandomExceptionsTestsP(msg) => 	throw new 	 SearchWithRandomExceptionsTests(msg)
      case RestExceptionP(msg) => 	throw new 	 RestException(msg)
      case RestTestParseExceptionP(msg) => 	throw new 	 RestTestParseException(msg)
      case _ => throw new 	 ElasticSearchException(failure.error)
    }



  }

}