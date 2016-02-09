function checkIsNonConnected($q, $timeout, $location, $state, $rootScope,
		$cookieStore) {
	var deffered = $q.defer();
	if (isConnected) {
		$timeout(deffered.reject, 0);
		$location.url('/');
	} else {

		$timeout(deffered.resolve, 0);
	}
	return deffered.promise;
}
function serializeData(data) {
	if (!angular.isObject(data)) {
		return ((data == null) ? '' : data.toString());
	}
	var buffer = [];
	for ( var name in data) {
		if (!data.hasOwnProperty(name)) {
			continue;
		}
		var value = data[name];
		buffer.push(encodeURIComponent(name) + "="
				+ encodeURIComponent((value == null) ? "" : value));
	}
	var source = buffer.join("&").replace(/%20/g, "+");
	return (source);
}
function checkIsClient($q, $timeout, $location, $state, $rootScope,
		$cookieStore) {
	var deffered = $q.defer();
	if ($rootScope.user.role !== "USER") {
		$timeout(deffered.reject, 0);
		$location.url('/');
	} else {

		$timeout(deffered.resolve, 0);
	}
	return deffered.promise;
}